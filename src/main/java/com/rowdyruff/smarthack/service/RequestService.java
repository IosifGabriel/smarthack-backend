package com.rowdyruff.smarthack.service;

import com.rowdyruff.domain.Request;
import com.rowdyruff.smarthack.model.RequestSubmission;

public interface RequestService extends GenericService<Request> {

	public Request createRequest(RequestSubmission submission, String jwt);
	
}
