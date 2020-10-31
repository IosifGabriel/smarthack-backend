package com.rowdyruff.smarthack.model;

import java.util.List;

import lombok.Data;

@Data
public class RequestSubmission {
	
	private List<Integer> documentIds;
	
	private Integer institutionId;

}
