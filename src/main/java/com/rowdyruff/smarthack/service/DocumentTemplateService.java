package com.rowdyruff.smarthack.service;

import java.util.List;

import com.rowdyruff.domain.DocumentTemplate;

public interface DocumentTemplateService extends GenericService<DocumentTemplate> {

	public List<DocumentTemplate> getTemplatesFromInstitution(Integer institutionId);
	
}
