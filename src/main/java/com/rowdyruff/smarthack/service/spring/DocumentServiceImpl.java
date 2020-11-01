package com.rowdyruff.smarthack.service.spring;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudmersive.client.ConvertDocumentApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
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
	
	public byte[] toPdf(byte[] docx) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		
		ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
		Apikey.setApiKey("06ed5ce4-7051-450e-bb3f-2f93bd1f50c7");
		ConvertDocumentApi apiInstance = new ConvertDocumentApi();
		File file =null; 
		try {
			file = File.createTempFile("temp", null);
			FileUtils.writeByteArrayToFile(file, docx);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
		    byte[] result = apiInstance.convertDocumentDocToPdf(file);
		    
		    return result;
		} catch (ApiException e) {
		    System.err.println("Exception when calling ConvertDocumentApi#convertDocumentDocToPdf");
		    e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Document createDocument(Request request, Response response) {
		Document document = new Document();
		DocumentTemplate template = request.getRequestedDocumentTemplate();
		
		document.setName(template.getName() + " " + request.getRequester().getLastName());
		document.setInstitution(request.getInstitution());
		document.setOwnerUser(request.getRequester());
		document.setTemplate(template);
		document.setReleaseDate(new Date());
		
		String sDate1="11/01/2020";  
	    Date date;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
			document.setExpirationDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		
		if (request.getCompletedFieldsMap() != null) {
			byte[] docx = buildDocxDocument(template, request.getCompletedFieldsMap());
			document.setDocumentBlob(toPdf(docx));
		}
		
		return documentRepository.create(document);
	}
	
	public List<Document> getDocumentsOfUser(Integer userId) {
		var docs = documentRepository.findAll();
		docs = docs.stream().filter(doc -> doc.getOwnerUser() != null && userId.equals(doc.getOwnerUser().getId().intValue())).collect(Collectors.toList());
		
		return docs;
	}
	
	public List<String> getPlaceholdersFromDoc(byte[] doc) {
		List<String> placeholders = new ArrayList<String>();
		XWPFDocument document = null;
		try {
			document = new XWPFDocument(new ByteArrayInputStream(doc));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (XWPFParagraph p : document.getParagraphs()) {
			 for (XWPFRun r : p.getRuns()) {
				  String text = r.getText(0);
				  if (text == null || text.isBlank())
					  continue;
				  
				  Integer startIndex = text.indexOf("[[") + 1;
				  Integer endIndex = text.indexOf("]]") + 1;
				  if (startIndex > endIndex || startIndex > text.length() || endIndex > text.length())
					  continue;
				  String word = text.substring(text.indexOf("[[") + 1, text.indexOf("]]") + 1);
				  if (word != null && !word.isEmpty() && word.length() > 0) {
					  placeholders.add(word);
				  }
			 }
		}
		
		return placeholders;
	}
}
	
