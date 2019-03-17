package com.vijay.filehandler.springbootfilehandler.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Response {
	private String message;
	private String fileName;
	private Date uploadDate;
	
	
	
	public Response() {
		super();
	}

	public Response(String message, String fileName, Date uploadDate) {
		super();
		this.message = message;
		this.fileName = fileName;
		this.uploadDate = uploadDate;
	}

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

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + ", fileName=" + fileName + ", uploadDate=" + uploadDate + "]";
	}

	
}
