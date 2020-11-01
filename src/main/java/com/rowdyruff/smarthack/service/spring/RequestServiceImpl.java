package com.rowdyruff.smarthack.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Document;
import com.rowdyruff.domain.DocumentTemplate;
import com.rowdyruff.domain.Institution;
import com.rowdyruff.domain.Request;
import com.rowdyruff.domain.RequestStatus;
import com.rowdyruff.domain.User;
import com.rowdyruff.repository.RequestRepository;
import com.rowdyruff.repository.UserRepository;
import com.rowdyruff.smarthack.model.RequestSubmission;
import com.rowdyruff.smarthack.service.DocumentService;
import com.rowdyruff.smarthack.service.DocumentTemplateService;
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
	DocumentTemplateService documentServiceTemplate;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	public RequestServiceImpl(RequestRepository requestRepository) {
		super.setRepository(requestRepository);
		this.requestRepository = requestRepository;
	}
	
	public List<Request> getRequestsOfClerk(Integer userId) {
		var requests = requestRepository.findAll();
		requests = requests.stream().filter(req -> req.getInstitution() != null && req.getInstitution().getId().equals(userId)).collect(Collectors.toList());
		
		return requests;
	}
	
	private byte[] createPdfFromFields(Map<String, String> fieldsMap, DocumentTemplate template) {
		byte[] docx = documentService.buildDocxDocument(template, fieldsMap);
		byte[] pdf = documentService.toPdf(docx);
		
		return pdf;
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
		
		request.setRequestedDocumentTemplate(documentServiceTemplate.getItem(requestSubmission.getRequestedDocumentTemplateId()));
		
		if (requestSubmission.getCompletedFieldsMap() != null && !requestSubmission.getCompletedFieldsMap().isEmpty()) {
			request.setCompletedFieldsMap(requestSubmission.getCompletedFieldsMap());
			byte[] pdf = createPdfFromFields(requestSubmission.getCompletedFieldsMap(), request.getRequestedDocumentTemplate());
			request.setGeneratedPdfFromFieldsMap(pdf);
		}
		
		String username = jwtUtils.extractUsername(jwt);
		User user = userRepository.findByUsername(username);
		
		request.setRequestStatus(RequestStatus.PENDING);
		request.setRequester(user);
		
		request = create(request);
		
		return request;
	}
	
}
