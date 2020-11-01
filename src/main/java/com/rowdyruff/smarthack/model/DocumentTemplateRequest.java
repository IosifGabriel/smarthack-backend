package com.rowdyruff.smarthack.model;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;

import lombok.Data;

@Data
public class DocumentTemplateRequest {
	
	private String name;
	
	private InputStream docTemplate;
	
	private FormDataContentDisposition fileDetail;
	
	private Integer institutionId;
	
}
