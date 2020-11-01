package com.rowdyruff.smarthack.service;

import java.util.List;

import com.rowdyruff.domain.Request;
import com.rowdyruff.smarthack.model.RequestSubmission;

public interface RequestService extends GenericService<Request> {

	public Request createRequest(RequestSubmission submission, String jwt);
	
	public List<Request> getRequestsOfClerk(Integer userId);
	
}
