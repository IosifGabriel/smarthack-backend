package com.rowdyruff.smarthack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rowdyruff.domain.Request;
import com.rowdyruff.smarthack.model.RequestSubmission;
import com.rowdyruff.smarthack.service.RequestService;

@RestController
@RequestMapping("/requests")
public class RequestController extends GenericController<Request> {

	private static final long serialVersionUID = -4583967600184696319L;
	
	@Autowired
	RequestService requestService;
	
	public RequestController(RequestService requestService) {
		super(requestService);
	}
	
	@PostMapping
	public ResponseEntity<?> submitRequest(@RequestHeader("Authorization") String jwt, @RequestBody RequestSubmission requestSubmission) {
		try {
			Request request = requestService.createRequest(requestSubmission, jwt);
			return ResponseEntity.ok(request);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@Override
	protected Boolean isNew(Request item) {
		return (item.getId() == null) || item.getId() < 1;
	}

	@Override
	protected Request getEmptyItem() {
		return new Request();
	}
	
	
}
