package com.rowdyruff.smarthack.model;

import java.util.Map;

import lombok.Data;

@Data
public class DocumentTemplateRequest {
	
	private String name;
	
	private Map<String, String> fieldsMap;
	
}
