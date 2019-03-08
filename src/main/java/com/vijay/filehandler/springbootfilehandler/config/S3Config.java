package com.vijay.filehandler.springbootfilehandler.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {

	private final Logger LOGGER = LoggerFactory.getLogger("S3Config.class");
	
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
    
    @Bean
    public AmazonS3 initializeAmazonClient() {
    	LOGGER.info(">>> Initializing S3 client");
    	
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       AmazonS3 s3client = AmazonS3ClientBuilder.standard()
    		   .withRegion(Regions.fromName(region))
               .withCredentials(new AWSStaticCredentialsProvider(credentials))
               .build();
       
       return s3client;
    }

}
