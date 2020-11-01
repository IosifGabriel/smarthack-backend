package com.rowdyruff.smarthack.service.spring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Request;
import com.rowdyruff.domain.RequestStatus;
import com.rowdyruff.domain.Response;
import com.rowdyruff.domain.User;
import com.rowdyruff.repository.ResponseRepository;
import com.rowdyruff.repository.UserRepository;
import com.rowdyruff.smarthack.model.ResponseSubmission;
import com.rowdyruff.smarthack.service.DocumentService;
import com.rowdyruff.smarthack.service.RequestService;
import com.rowdyruff.smarthack.service.ResponseService;
import com.rowdyruff.smarthack.utils.JwtUtils;

@Service
@Transactional
public class ResponseServiceImpl extends GenericServiceImpl<Response> implements ResponseService {
	
	ResponseRepository responseRepository;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RequestService requestService;
	
	@Autowired
	DocumentService documentService;

	@Autowired
	public ResponseServiceImpl(ResponseRepository responseRepository) {
		super.setRepository(responseRepository);
		this.responseRepository = responseRepository;
	}
	
	public Response submitResponse(ResponseSubmission submission, String jwt) {
		Response response = new Response();
		
		Map<Integer, String> statuses = submission.getStatuses();
		
		boolean accepted = true;		
		for (var key : statuses.keySet()) {
			if (!statuses.get(key).equals("null"))
				accepted = false;
		}
		
		Request req = requestService.getItem(submission.getRequestId());
		req.setRequestStatus(accepted ? RequestStatus.APROVED : RequestStatus.DECLINIED);
		response.setRequest(req);
		
		String username = jwtUtils.extractUsername(jwt);
		User clerk = userRepository.findByUsername(username);
		response.setClerk(clerk);
		
		response.setDocumentStatuses(statuses);
		response.setRequestStatus(accepted ? RequestStatus.APROVED : RequestStatus.DECLINIED);
		
		response = create(response);
		
		if (accepted) {
			documentService.createDocument(req, response);
		}
		
		return response;
		
	}
}
