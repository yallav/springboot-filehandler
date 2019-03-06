package com.vijay.filehandler.springbootfilehandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.vijay.filehandler.springbootfilehandler.properties.FileStorageProperties;

@SpringBootApplication
public class SpringbootFilehandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFilehandlerApplication.class, args);
	}

}
