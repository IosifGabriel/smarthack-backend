package com.rowdyruff.smarthack.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import com.rowdyruff.domain.Document;
import com.rowdyruff.domain.DocumentTemplate;
import com.rowdyruff.domain.Request;
import com.rowdyruff.domain.Response;

public interface DocumentService extends GenericService<Document> {

	public Document createDocument(Request request, Response response);
	
	public byte[] buildDocxDocument(DocumentTemplate template, Map<String, String> fieldsMap);
	
	public byte[] toPdf(byte[] docx);
	
	public List<Document> getDocumentsOfUser(Integer userId);
	
}
