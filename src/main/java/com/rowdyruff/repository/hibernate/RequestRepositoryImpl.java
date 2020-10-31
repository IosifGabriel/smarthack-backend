package com.rowdyruff.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Request;
import com.rowdyruff.repository.RequestRepository;

@Repository
@Transactional(readOnly = false)
public class RequestRepositoryImpl extends GenericRepositoryImpl<Request> implements RequestRepository {

	public RequestRepositoryImpl() {
		super.setClazz(Request.class);
	}
	
	
}