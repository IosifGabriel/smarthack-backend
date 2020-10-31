package com.rowdyruff.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Institution;
import com.rowdyruff.repository.InstitutionRepository;

@Repository
@Transactional(readOnly = false)
public class InstitutionRepositoryImpl extends GenericRepositoryImpl<Institution> implements InstitutionRepository {

	public InstitutionRepositoryImpl() {
		super.setClazz(Institution.class);
	}
	
	
}