package com.rowdyruff.smarthack.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rowdyruff.domain.DocumentTemplate;
import com.rowdyruff.smarthack.model.DocumentTemplateRequest;
import com.rowdyruff.smarthack.service.DocumentTemplateService;
import com.rowdyruff.smarthack.service.GenericService;
import com.rowdyruff.smarthack.service.InstitutionService;

@RestController
@RequestMapping("/documentTemplates")
public class DocumentTemplateController extends GenericController<DocumentTemplate> {

	private static final long serialVersionUID = -816838120445629950L;
	
	@Autowired
	DocumentTemplateService documentTemplateService;
	
	@Autowired
	InstitutionService institutionService;

	public DocumentTemplateController(GenericService<DocumentTemplate> service) {
		super(service);
	}
	
	@PostMapping
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ResponseEntity<?> addDocumentTemplate(@ModelAttribute DocumentTemplateRequest request, @RequestParam("file") MultipartFile file) {
		String msg = null;

		try {
			var template = new DocumentTemplate();
			template.setInstitution(institutionService.getItem(request.getInstitutionId()));
			template.setName(request.getName());
			if(file == null) {
				System.out.print("E NULL");
			}
			byte[] arr = file.getBytes();
			template.setDocTemplate(arr);
			
			msg = saveItem(template);
			return ResponseEntity.ok(msg);
		} catch (Exception ex) {
			System.out.print("AICI E PROBLEMA");
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}
	
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<?> editDocumentTemplate(@PathVariable("id") Integer id, @RequestBody DocumentTemplateRequest request) {
//		DocumentTemplate item = service.getItem(id);
//		
//		
//		if (request.getDocTemplate() != null)
//			item.setDocTemplate(request.getDocTemplate());
//		
//		if (request.getName() != null)
//			item.setName(request.getName());
//		
//		return ResponseEntity.ok(service.update(item));
//	}
	
	@GetMapping(value="/{institutionId}")
	public ResponseEntity<?> getTemplatesFromInstitution(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(documentTemplateService.getTemplatesFromInstitution(id));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@Override
	protected DocumentTemplate getEmptyItem() {
		return new DocumentTemplate();
	}

	@Override
	protected Boolean isNew(DocumentTemplate item) {
		return (item.getId() == null) || item.getId() < 1;
	}
	
}
