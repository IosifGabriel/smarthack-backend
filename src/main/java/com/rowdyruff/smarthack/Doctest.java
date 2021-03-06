package com.rowdyruff.smarthack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlgraphics.util.MimeConstants;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import com.cloudmersive.client.ConvertDocumentApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.rowdyruff.domain.DocumentTemplate;

public class Doctest {
	
	public static String pathToDoc = "C:\\Users\\Alex\\Downloads\\Document.docx";
	
	private static XWPFDocument load(String name) {
		XWPFDocument document = null;
		try {
			document = new XWPFDocument(new FileInputStream(new File(name)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return document;
	}
	
	private static XWPFDocument replacePlaceH(String placeH, String replacer, XWPFDocument document) {
		
	for (XWPFParagraph p : document.getParagraphs()) {
		 //List<XWPFRun> runs = p.getRuns();
		 for (XWPFRun r : p.getRuns()) {
			  String text = r.getText(0);
			  if (text != null && text.contains(placeH)) {
			     text = text.replace(placeH, replacer);
			     r.setText(text,0);
			     }
			  }
		 
	}
	
	return document;
		
	}
	
	public static void toPdf(byte[] arr, Map<String, String> fieldsMap) throws Docx4JException {
		String target = "C:\\Users\\Alex\\Downloads\\resultingx.png";
		try {
			WordprocessingMLPackage wordMLPckg = Docx4J.load(new ByteArrayInputStream(arr));
			OutputStream os = new FileOutputStream(new File(target));
			
			FOSettings settings = Docx4J.createFOSettings();
		    settings.setWmlPackage(wordMLPckg);
		    settings.setApacheFopMime(MimeConstants.MIME_PNG);
		    Docx4J.toFO(settings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
		    os.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static List<String> getPlaceholdersFromDoc(byte[] doc) {
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
				  if (text == null)
					  continue;
				  
				  String word = text.substring(text.indexOf("[[") + 1, text.indexOf("]]") + 1);
				  if (word != null && !word.isEmpty() && word.length() > 0) {
					  placeholders.add(word);
				  }
			 }
		}
		
		return placeholders;
	}
	
	public static void main(String[] args) {
		
		try {
			byte[] arr = Files.readAllBytes(Paths.get("C:\\Users\\Alex\\Downloads\\DocumentReg.docx"));
			List<String> place = getPlaceholdersFromDoc(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		ApiClient defaultClient = Configuration.getDefaultApiClient();
//		
//		ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
//		Apikey.setApiKey("06ed5ce4-7051-450e-bb3f-2f93bd1f50c7");
//		// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//		//Apikey.setApiKeyPrefix("Token");
//		ConvertDocumentApi apiInstance = new ConvertDocumentApi();
//		File file = null;
//		try {
//			file = File.createTempFile("temp", null);
//			byte[] docx = FileUtils.readFileToByteArray(new File("C:\\Users\\Alex\\Downloads\\Document.docx"));
//			FileUtils.writeByteArrayToFile(file, docx);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		File inputFile = file; // File | Input file to perform the operation on.
//		try {
//		    byte[] result = apiInstance.convertDocumentDocToPdf(inputFile);
//		    FileUtils.writeByteArrayToFile(new File("C:\\Users\\Alex\\Downloads\\resultsxs.pdf"), result);
//		} catch (ApiException e) {
//		    System.err.println("Exception when calling ConvertDocumentApi#convertDocumentDocToPdf");
//		    e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
