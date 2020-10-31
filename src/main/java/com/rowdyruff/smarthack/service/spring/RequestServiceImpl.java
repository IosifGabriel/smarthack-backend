package com.rowdyruff.smarthack.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Document;
import com.rowdyruff.domain.Institution;
import com.rowdyruff.domain.Request;
import com.rowdyruff.domain.RequestStatus;
import com.rowdyruff.domain.User;
import com.rowdyruff.repository.RequestRepository;
import com.rowdyruff.repository.UserRepository;
import com.rowdyruff.smarthack.model.RequestSubmission;
import com.rowdyruff.smarthack.service.DocumentService;
import com.rowdyruff.smarthack.service.InstitutionService;
import com.rowdyruff.smarthack.service.RequestService;
import com.rowdyruff.smarthack.utils.JwtUtils;

@Service
@Transactional
public class RequestServiceImpl extends GenericServiceImpl<Request> implements RequestService {
	
	RequestRepository requestRepository;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	InstitutionService institutionService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	public RequestServiceImpl(RequestRepository requestRepository) {
		super.setRepository(requestRepository);
		this.requestRepository = requestRepository;
	}
	
	public Request createRequest(RequestSubmission requestSubmission, String jwt) {	
		Request request = new Request();
		request.setRequiredDocuments(new ArrayList<Document>());
		
		List<Integer> documentIds = requestSubmission.getDocumentIds();
		
		
		for (var id : documentIds) {
			request.getRequiredDocuments().add(documentService.getItem(id));
		}
		
		Institution institution = institutionService.getItem(requestSubmission.getInstitutionId());
		request.setInstitution(institution);
		
		String username = jwtUtils.extractUsername(jwt);
		User user = userRepository.findByUsername(username);
		
		request.setRequestStatus(RequestStatus.PENDING);
		request.setRequester(user);
		
		request = create(request);
		
		return request;
	}
	
}
