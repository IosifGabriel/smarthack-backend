package com.rowdyruff.smarthack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlgraphics.util.MimeConstants;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

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
	
	public static void main(String[] args) {
		Map<String, String> fields = new HashMap<>();
		fields.put("SJ_PLACEHOLDER", "Mandolina");
		fields.put("SJ_INLOCUIESC", "victor");
		fields.put("SJ_DATA", "castravete");
		
		var template = load(pathToDoc);
		
		for (String key : fields.keySet()) {
			String placeH = key;
			String replacer = fields.get(key);
			replacePlaceH(placeH, replacer, template);
		}
		
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			template.write(out);
			byte[] arr = out.toByteArray();
			
			toPdf(arr, fields);
			
			String target = "C:\\Users\\Alex\\Downloads\\resulting.docx";
			try {
				template.write(new FileOutputStream(new File(target)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception ex) {
			
		}
	}
	
}
