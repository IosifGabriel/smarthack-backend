package com.rowdyruff.smarthack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rowdyruff.domain.Request;
import com.rowdyruff.domain.User;
import com.rowdyruff.repository.UserRepository;
import com.rowdyruff.smarthack.model.RequestSubmission;
import com.rowdyruff.smarthack.service.DocumentService;
import com.rowdyruff.smarthack.service.RequestService;
import com.rowdyruff.smarthack.utils.JwtUtils;

@RestController
@RequestMapping("/requests")
public class RequestController extends GenericController<Request> {

	private static final long serialVersionUID = -4583967600184696319L;
	
	@Autowired
	RequestService requestService;
	
	@Autowired
	private JwtUtils jwtTokenUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DocumentService documentService;
	
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
	
	@GetMapping("/myClerkRequests")
	@ResponseBody
	public ResponseEntity<?> getItems(@RequestHeader("Authorization") String jwt) {
		try {
			String username = jwtTokenUtil.extractUsername(jwt);
			User user = userRepository.findByUsername(username);
			return ResponseEntity.ok(requestService.getRequestsOfClerk(user.getId().intValue()));
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
