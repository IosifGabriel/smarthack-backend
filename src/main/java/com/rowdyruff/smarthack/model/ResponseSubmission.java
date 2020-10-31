package com.rowdyruff.smarthack.model;

import java.util.Map;

import lombok.Data;

@Data
public class ResponseSubmission {

	private Map<Integer, String> statuses;
	
	private Integer requestId;
	
}
