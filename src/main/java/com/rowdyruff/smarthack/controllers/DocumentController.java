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

import com.rowdyruff.domain.Document;
import com.rowdyruff.domain.Institution;
import com.rowdyruff.domain.User;
import com.rowdyruff.repository.UserRepository;
import com.rowdyruff.smarthack.model.InstitutionRequest;
import com.rowdyruff.smarthack.service.DocumentService;
import com.rowdyruff.smarthack.utils.JwtUtils;

@RestController
@RequestMapping("/document")
public class DocumentController extends GenericController<Document> {
	
	private static final long serialVersionUID = 2537913914336345727L;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtils jwtTokenUtil;

	public DocumentController(DocumentService documentService) {
		super(documentService);
	}
	
//	@PostMapping
//	public ResponseEntity<?> addDocument(@RequestBody Integer requestId) {
//		String msg = null;
//		service.createDocument();
//		try {
//			msg = saveItem(new Document(request));
//			return ResponseEntity.ok(msg);
//		} catch (Exception ex) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
//		}
//	}
	
//	@GetMapping
//	@ResponseBody
//	@Override
//	public ResponseEntity<?> getItems(@RequestHeader("Authorization") String jwt) {
//		try {
//			String username = jwtTokenUtil.extractUsername(jwt);
//			User user = userRepository.findByUsername(username);
//			return ResponseEntity.ok(documentService.getDocumentsOfUser(user));
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
		
		
//	}
	
	@Override
	protected Document getEmptyItem() {
		return new Document();
	}

	@Override
	protected Boolean isNew(Document item) {
		return (item.getId() == null) || item.getId() < 1;
	}
	
	

}
