package com.rowdyruff.smarthack.service;

import java.io.FileNotFoundException;

import com.rowdyruff.domain.Document;
import com.rowdyruff.domain.Request;
import com.rowdyruff.domain.Response;

public interface DocumentService extends GenericService<Document> {

	public Document createDocument(Request request, Response response);
	
	
	
}
