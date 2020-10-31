package com.rowdyruff.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "DOCUMENT_TEMPLATES")
public class DocumentTemplate implements Serializable {

	private static final long serialVersionUID = -7016040288480054831L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Lob
	@Column(name = "DOC_TEMPLATE")
	private byte[] docTemplate;

	public DocumentTemplate(String name, byte[] docTemplate) {
		this.name = name;
		this.docTemplate = docTemplate;
	}

	public DocumentTemplate() {
		
	}

}
