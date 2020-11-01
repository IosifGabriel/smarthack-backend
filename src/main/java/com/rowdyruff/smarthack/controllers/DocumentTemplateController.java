package com.rowdyruff.smarthack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rowdyruff.domain.DocumentTemplate;
import com.rowdyruff.smarthack.model.DocumentTemplateRequest;
import com.rowdyruff.smarthack.service.DocumentTemplateService;
import com.rowdyruff.smarthack.service.GenericService;

@RestController
@RequestMapping("/documentTemplates")
public class DocumentTemplateController extends GenericController<DocumentTemplate> {

	private static final long serialVersionUID = -816838120445629950L;
	
	@Autowired
	DocumentTemplateService documentTemplateService;

	public DocumentTemplateController(GenericService<DocumentTemplate> service) {
		super(service);
	}
	
	@PostMapping
	public ResponseEntity<?> addDocumentTemplate(@RequestBody DocumentTemplateRequest request) {
		String msg = null;
		try {
			msg = saveItem(new DocumentTemplate(request.getName(), request.getDocTemplate()));
			return ResponseEntity.ok(msg);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> editDocumentTemplate(@PathVariable("id") Integer id, @RequestBody DocumentTemplateRequest request) {
		DocumentTemplate item = service.getItem(id);
		if (request.getDocTemplate() != null)
			item.setDocTemplate(request.getDocTemplate());
		
		if (request.getName() != null)
			item.setName(request.getName());
		
		return ResponseEntity.ok(service.update(item));
	}
	
//	@GetMapping(value="/{institutionId}")
//	public ResponseEntity<?> getTemplatesFromInstitution(@PathVariable("id") Integer id) {
//		try {
//			return ResponseEntity.ok(documentTemplateService.getTemplatesFromInstitution(id));
//		}
//	}

	@Override
	protected DocumentTemplate getEmptyItem() {
		return new DocumentTemplate();
	}

	@Override
	protected Boolean isNew(DocumentTemplate item) {
		return (item.getId() == null) || item.getId() < 1;
	}
	
}
