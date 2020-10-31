package com.rowdyruff.smarthack.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Request;
import com.rowdyruff.repository.RequestRepository;
import com.rowdyruff.smarthack.service.RequestService;

@Service
@Transactional
public class RequestServiceImpl extends GenericServiceImpl<Request> implements RequestService {
	
	RequestRepository requestRepository;

	@Autowired
	public RequestServiceImpl(RequestRepository requestRepository) {
		super.setRepository(requestRepository);
		this.requestRepository = requestRepository;
	}
	
}
