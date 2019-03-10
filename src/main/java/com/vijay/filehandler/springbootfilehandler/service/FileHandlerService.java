package com.vijay.filehandler.springbootfilehandler.service;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.vijay.filehandler.springbootfilehandler.model.Response;
import com.vijay.filehandler.springbootfilehandler.model.UserData;

@Service
public class FileHandlerService {

	private final Logger LOGGER = LoggerFactory.getLogger(FileHandlerService.class);
	
	@Autowired
	private AmazonS3 s3client;
    
	@Autowired
	TextToImage textToImage;
	
	@Value("${awsS3Properties.endpointUrl}")
    private String endpointUrl;

	@Value("${awsS3Properties.region}")
	private String region;
	
	@Value("${awsS3Properties.bucketName}")
    private String bucketName;

    @Value("${awsS3Properties.secretKey}")
    private String secretKey;

    @Value("${awsS3Properties.accessKey}")
    private String accessKey;
    
    public String uploadFile(String uploadFileName, MultipartFile multipartFile) {

        String fileUrl = "";
        try {
        	
        	ObjectMetadata metadata = new ObjectMetadata();
        	
        	if(multipartFile.isEmpty()) {
        		LOGGER.info(">>> ===================== Import File - failed due to empty file =====================");
        		return "File upload was unsuccessful";
        	}else {
        		metadata.setContentLength(multipartFile.getSize());
            	
                s3client.putObject(
                		new PutObjectRequest(bucketName, uploadFileName, multipartFile.getInputStream(),metadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                
                fileUrl = endpointUrl + "/" + bucketName + "/" + uploadFileName;
                LOGGER.info(">>> ===================== Import File - Finished =====================");
        	}
        	
        } catch (IOException e) {
        	e.printStackTrace();
        	LOGGER.info(">>> ===================== Exception was thrown due to IO issue ===================== ");
        	return "File upload was unsuccessful";
        } catch(AmazonClientException e) {
        	e.printStackTrace();
            LOGGER.info(">>> ===================== Exception was thrown due to Amazon access issue =====================  ");
            return "File upload was unsuccessful";
        }
        
        return "File upload was successful :: "+uploadFileName;
    }
    
    public Response createAndUploadFile(List<UserData> userData) {
    	
    	String fileToUpload = textToImage.convertTextToImage(userData);
    	File file = new File(fileToUpload);
    	String uploadFileName = file.getName();
    	Response res = new Response();
    	
    	try {
    		s3client.putObject(
            		new PutObjectRequest(bucketName, uploadFileName, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
    		res.setMessage("File upload was successful");
            LOGGER.info(">>> Image file was uploaded successfully");
    	}catch(Exception e) {
    		e.printStackTrace();
    		LOGGER.info(">>> Image file upload failed");
    		res.setMessage("Image file upload failed");
    		res.setFileName("");
    		return res;
    	}
    	
    	res.setFileName(uploadFileName);
    	return res;
    }
    
    public ByteArrayOutputStream downloadFile(String objectName) {
    	
    	ByteArrayOutputStream baos = null;
    	try {
    		boolean exists = s3client.doesObjectExist(bucketName, objectName);
    		
    		if(exists) {
    			S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, objectName));
                InputStream is = s3object.getObjectContent();
                baos = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[4096];
                while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                    baos.write(buffer, 0, len);
                }
                LOGGER.info(">>> ===================== Download File - Completed =====================");
    		}else {
    			LOGGER.info(">>> ERROR ::: Key does not exist");
    			return null;
    		}
        	
    	}catch (Exception e) {
        	LOGGER.info(">>> ===================== Download File - Failed =====================");
        	try {
				baos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		e.printStackTrace();
    		
    		return null;
    	}
    	
    	LOGGER.info(">>> ===================== Download File - Finished =====================");
        return baos;
    }
}
