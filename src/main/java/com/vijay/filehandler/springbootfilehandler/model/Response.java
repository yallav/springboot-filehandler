package com.vijay.filehandler.springbootfilehandler.model;

import org.springframework.stereotype.Component;

@Component
public class Response {
	private String message;
	private String fileName;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String toString() {
		return "Response [message=" + message + ", fileName=" + fileName + "]";
	}
	
}
