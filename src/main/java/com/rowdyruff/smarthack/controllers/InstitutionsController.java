package com.rowdyruff.smarthack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rowdyruff.domain.Institution;
import com.rowdyruff.smarthack.model.InstitutionRequest;
import com.rowdyruff.smarthack.service.InstitutionService;

@RestController
@RequestMapping("/institutions")
public class InstitutionsController extends GenericController<Institution> {
	
	private static final long serialVersionUID = 2537913914336345727L;
	
	@Autowired
	InstitutionService institutionService;

	public InstitutionsController(InstitutionService institutionService) {
		super(institutionService);
	}
	
	@PostMapping
	public ResponseEntity<?> addInstitution(@RequestBody InstitutionRequest request) {
		String msg = null;
		try {
			msg = saveItem(new Institution(request));
			return ResponseEntity.ok(msg);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> editInstitution(@PathVariable("id") Integer id, @RequestBody InstitutionRequest request) {
		Institution item = service.getItem(id);
		if (request.getAbreviation() != null)
			item.setAbreviation(request.getAbreviation());
		
		if (request.getAddress() != null)
			item.setAddress(request.getAddress());
		
		if (request.getName() != null)
			item.setName(request.getName());
		
		return ResponseEntity.ok(service.update(item));
	}
	
	
	@Override
	protected Institution getEmptyItem() {
		return new Institution();
	}

	@Override
	protected Boolean isNew(Institution item) {
		return (item.getId() == null) || item.getId() < 1;
	}
	
	

}
