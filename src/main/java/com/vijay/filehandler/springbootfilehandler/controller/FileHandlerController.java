package com.vijay.filehandler.springbootfilehandler.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.filehandler.springbootfilehandler.model.Response;
import com.vijay.filehandler.springbootfilehandler.model.UserData;
import com.vijay.filehandler.springbootfilehandler.service.FileHandlerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class FileHandlerController {

	private final Logger LOGGER = LoggerFactory.getLogger(FileHandlerController.class);
		
	private FileHandlerService fileHandlerService;

    @Autowired
    FileHandlerController(FileHandlerService fileHandlerService) {
        this.fileHandlerService = fileHandlerService;
    }

    @PostMapping("/uploadfile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
    	LOGGER.info(">>> Inside file upload controller");
    	String fileName = file.getOriginalFilename();
        return this.fileHandlerService.uploadFile(fileName, file);
    }
    
    @PostMapping("/createanduploadfile")
    public @ResponseBody List<Response> createFile(@RequestBody List<UserData> userData) {
    	LOGGER.info(">>> Inside image create and upload handler");
    	return this.fileHandlerService.createAndUploadFile(userData);
    }
    
    @GetMapping("/listfiles")
    public @ResponseBody List<Response> getTheFileObjectsDetails(){
    	LOGGER.info(">>> Fetched the file obejcts details from AWS S3");
    	return	this.fileHandlerService.listObjects();
    }
    
    @GetMapping("/downloadfile/{filename:.*}")
    public @ResponseBody Response downloadFile(@PathVariable String filename) {
    	LOGGER.info(">>> Inside file download controller");
    	return this.fileHandlerService.downloadFile(filename);
    }

}
