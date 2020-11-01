package com.rowdyruff.smarthack.model;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.sun.jersey.core.header.FormDataContentDisposition;

import lombok.Data;

@Data
public class DocumentTemplateRequest {
	
	private String name;
	
	private Integer institutionId;
	
	//private MultipartFile file;
	
}
