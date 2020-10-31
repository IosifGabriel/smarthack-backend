package com.rowdyruff.smarthack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rowdyruff.domain.Response;
import com.rowdyruff.smarthack.model.ResponseSubmission;
import com.rowdyruff.smarthack.service.ResponseService;

@RestController
@RequestMapping("/responses")
public class ResponseController extends GenericController<Response> {
	
	private static final long serialVersionUID = -346987202037049943L;
	
	@Autowired
	ResponseService responseService;

	public ResponseController(ResponseService responseService) {
		super(responseService);
	}
	
	@PostMapping
	public ResponseEntity<?> submitResponse(@RequestHeader("Authorization") String jwt, @RequestBody ResponseSubmission responseSubmission) {
		try {
			Response response = responseService.submitResponse(responseSubmission, jwt);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@Override
	protected Response getEmptyItem() {
		return new Response();
	}

	@Override
	protected Boolean isNew(Response item) {
		return (item.getId() == null) || item.getId() < 1;
	}

}
