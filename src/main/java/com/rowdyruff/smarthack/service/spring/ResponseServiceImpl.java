package com.rowdyruff.smarthack.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Response;
import com.rowdyruff.repository.ResponseRepository;
import com.rowdyruff.smarthack.service.ResponseService;

@Service
@Transactional
public class ResponseServiceImpl extends GenericServiceImpl<Response> implements ResponseService {
	
	ResponseRepository responseRepository;

	@Autowired
	public ResponseServiceImpl(ResponseRepository responseRepository) {
		super.setRepository(responseRepository);
		this.responseRepository = responseRepository;
	}
	
}
