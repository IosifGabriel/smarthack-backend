package com.rowdyruff.domain;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Convert(converter = HashMapConverter.class)
    private Map<Integer, String> documentStatuses;

}
