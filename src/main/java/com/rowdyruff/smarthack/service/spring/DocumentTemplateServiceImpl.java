package com.rowdyruff.smarthack.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.DocumentTemplate;
import com.rowdyruff.repository.DocumentTemplateRepository;
import com.rowdyruff.smarthack.service.DocumentTemplateService;

@Service
@Transactional
public class DocumentTemplateServiceImpl extends GenericServiceImpl<DocumentTemplate> implements DocumentTemplateService {
	
	DocumentTemplateRepository documentTemplateRepository;

	@Autowired
	public DocumentTemplateServiceImpl(DocumentTemplateRepository documentTemplateRepository) {
		super.setRepository(documentTemplateRepository);
		this.documentTemplateRepository = documentTemplateRepository;
	}
	
}
