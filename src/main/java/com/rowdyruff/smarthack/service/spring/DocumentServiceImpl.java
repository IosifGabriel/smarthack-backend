package com.rowdyruff.smarthack.service.spring;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.Document;
import com.rowdyruff.domain.DocumentTemplate;
import com.rowdyruff.domain.Request;
import com.rowdyruff.domain.Response;
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
	
	public byte[] buildDocxDocument(DocumentTemplate template, Map<String, String> fieldsMap) {
		byte[] arr = template.getDocTemplate();
		XWPFDocument document = null;
		try {
			document = new XWPFDocument(new ByteArrayInputStream(arr));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (String key : fieldsMap.keySet()) {
			String placeH = key;
			String replacer = fieldsMap.get(key);
			for (XWPFParagraph p : document.getParagraphs()) {
				 for (XWPFRun r : p.getRuns()) {
					  String text = r.getText(0);
					  if (text != null && text.contains(placeH)) {
					     text = text.replace(placeH, replacer);
					     r.setText(text,0);
					     }
					  }
			}
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			document.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return out.toByteArray();
	}
	
	public Document createDocument(Request request, Response response) {
		Document document = new Document();
		DocumentTemplate template = request.getRequestedDocumentTemplate();
		
		
		document.setName(template.getName() + " " + request.getRequester().getLastName());
		document.setInstitution(request.getInstitution());
		document.setOwnerUser(request.getRequester());
		document.setTemplate(template);
		
		if (request.getCompletedFieldsMap() != null)
			document.setDocumentBlob(buildDocxDocument(template, request.getCompletedFieldsMap()));
			
		
		
		return document;
	}
	
	
	
}
