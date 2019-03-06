package com.vijay.filehandler.springbootfilehandler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.filehandler.springbootfilehandler.service.FileHandlerService;

@RestController
@RequestMapping("/api")
public class FileHandlerController {

	private FileHandlerService fileHandlerService;

    @Autowired
    FileHandlerController(FileHandlerService fileHandlerService) {
        this.fileHandlerService = fileHandlerService;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.fileHandlerService.uploadFile(file);
    }

}
