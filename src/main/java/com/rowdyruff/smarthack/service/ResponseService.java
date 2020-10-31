package com.rowdyruff.smarthack.service;

import com.rowdyruff.domain.Response;
import com.rowdyruff.smarthack.model.ResponseSubmission;

public interface ResponseService extends GenericService<Response> {

	public Response submitResponse(ResponseSubmission submission, String jwt);
	
}
