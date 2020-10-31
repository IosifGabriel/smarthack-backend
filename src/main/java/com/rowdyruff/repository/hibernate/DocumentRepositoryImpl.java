package com.rowdyruff.repository.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Document;
import com.rowdyruff.repository.DocumentRepository;

@Repository
@Transactional(readOnly = false)
public class DocumentRepositoryImpl extends GenericRepositoryImpl<Document> implements DocumentRepository {

	public DocumentRepositoryImpl() {
		super.setClazz(Document.class);
	}
	
	
}
