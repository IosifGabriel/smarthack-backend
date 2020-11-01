package com.rowdyruff.smarthack.service.spring;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.DocumentTemplate;
import com.rowdyruff.repository.DocumentTemplateRepository;
import com.rowdyruff.smarthack.service.DocumentTemplateService;
import com.rowdyruff.smarthack.service.InstitutionService;

@Service
@Transactional
public class DocumentTemplateServiceImpl extends GenericServiceImpl<DocumentTemplate> implements DocumentTemplateService {
	
	DocumentTemplateRepository documentTemplateRepository;
	
	@Autowired
	InstitutionService institutionService;

	@Autowired
	public DocumentTemplateServiceImpl(DocumentTemplateRepository documentTemplateRepository) {
		super.setRepository(documentTemplateRepository);
		this.documentTemplateRepository = documentTemplateRepository;
	}
	
	public List<DocumentTemplate> getTemplatesFromInstitution(Integer institutionId) {
		var templates = documentTemplateRepository.findAll();
		if (templates != null) {
			templates = templates.stream().filter(templ -> templ.getInstitution() != null && templ.getInstitution().getId().equals(institutionId)).collect(Collectors.toList());
		}
		
		return templates;
	}
	
}
