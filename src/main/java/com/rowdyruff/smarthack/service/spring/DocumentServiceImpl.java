package com.rowdyruff.smarthack.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Document;
import com.rowdyruff.domain.Request;
import com.rowdyruff.repository.DocumentRepository;
import com.rowdyruff.smarthack.service.DocumentService;
import com.rowdyruff.smarthack.service.RequestService;

@Service
@Transactional
public class DocumentServiceImpl extends GenericServiceImpl<Document> implements DocumentService {
	
	DocumentRepository documentRepository;

	@Autowired
	RequestService requestService;
	
	@Autowired
	public DocumentServiceImpl(DocumentRepository documentRepository) {
		super.setRepository(documentRepository);
		this.documentRepository = documentRepository;
	}
	
//	public Document createDocument(Integer requestId) {
//		Request request = requestService.getItem(requestId);
//		
//		
//	}
	
}
