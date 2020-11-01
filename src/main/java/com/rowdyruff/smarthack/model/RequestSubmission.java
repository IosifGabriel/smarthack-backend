package com.rowdyruff.smarthack.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RequestSubmission {
	
	private List<Integer> documentIds;
	
	private Integer institutionId;
	
	private Map<String, String> completedFieldsMap;
	
	private Integer requestedDocumentTemplateId;

}
