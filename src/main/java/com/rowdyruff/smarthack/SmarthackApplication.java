package com.rowdyruff.smarthack;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.rowdyruff.domain.Document;
import com.rowdyruff.domain.DocumentTemplate;
import com.rowdyruff.domain.Institution;
import com.rowdyruff.domain.User;
import com.rowdyruff.repository.DocumentRepository;
import com.rowdyruff.repository.DocumentTemplateRepository;
import com.rowdyruff.repository.InstitutionRepository;
import com.rowdyruff.repository.UserRepository;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class SmarthackApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Autowired
	private DocumentTemplateRepository documentTemplateRepository;
	
	@Autowired
	private DocumentRepository documentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SmarthackApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		try {
			userRepository.create(new User("foo", "foo"));
		} catch (Exception ex) {
			return;
		}		
		
		Institution institution = new Institution();
		institution.setAbreviation("ANAF");
		institution.setName("Agentia nationala de administratie fiscala");
		institution.setAddress("Strada gusa");
		institution = institutionRepository.create(institution);
		
		DocumentTemplate templ = new DocumentTemplate();
		byte[] docx = {};
		templ.setDocTemplate(docx);
		templ.setName("CErere Y");
		templ = documentTemplateRepository.create(templ);
		
		Document document = new Document();
		document.setName("Hardcoded");
		document.setExpirationDate(new Date());
		document.setReleaseDate(new Date());
		
		document.setB64Pdf("NU E PDF");
		document.setInstitution(institution);
		document.setOwnerUser(userRepository.findOne(1));
		document.setRequest(null);
		document.setTemplate(templ);
		
		Map<String, String> fields = new HashMap<>();
		fields.put("Nume", "Mandolina");
		fields.put("Prenume", "victor");
		fields.put("Domiciliu", "castravete");
		
		document.setFieldsMap(fields);
		documentRepository.create(document);
		
	}

}
