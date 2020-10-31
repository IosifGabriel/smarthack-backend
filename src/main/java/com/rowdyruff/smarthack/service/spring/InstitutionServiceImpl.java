package com.rowdyruff.smarthack.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Institution;
import com.rowdyruff.repository.InstitutionRepository;
import com.rowdyruff.smarthack.service.InstitutionService;

@Service
@Transactional
public class InstitutionServiceImpl extends GenericServiceImpl<Institution> implements InstitutionService {
	
	InstitutionRepository institutionRepository;

	@Autowired
	public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
		super.setRepository(institutionRepository);
		this.institutionRepository = institutionRepository;
	}
	
}
