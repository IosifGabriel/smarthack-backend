package com.rowdyruff.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Response;
import com.rowdyruff.repository.ResponseRepository;

@Repository
@Transactional(readOnly = false)
public class ResponseRepositoryImpl extends GenericRepositoryImpl<Response> implements ResponseRepository {

	public ResponseRepositoryImpl() {
		super.setClazz(Response.class);
	}
	
	
}