package com.rowdyruff.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.DocumentTemplate;
import com.rowdyruff.repository.DocumentTemplateRepository;

@Repository
@Transactional(readOnly = false)
public class DocumentTemplateRepositoryImpl extends GenericRepositoryImpl<DocumentTemplate> implements DocumentTemplateRepository {

	public DocumentTemplateRepositoryImpl() {
		super.setClazz(DocumentTemplate.class);
	}
	
	
}