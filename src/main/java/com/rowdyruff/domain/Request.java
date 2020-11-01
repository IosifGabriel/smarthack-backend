package com.rowdyruff.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "REQUESTS")
public class Request implements Serializable {

	private static final long serialVersionUID = -7102890647085725150L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private RequestStatus requestStatus;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="REQUESTER_ID", nullable=false, updatable=true, insertable=true)
	private User requester;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="RESPONDER_ID", nullable=true, updatable=true, insertable=true)
	private User responder;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="INSTITUTION_ID", nullable=false, updatable=true, insertable=true)
	private Institution institution;
	
	@ManyToMany(cascade={CascadeType.PERSIST}, targetEntity=Document.class)
	@JoinTable(name="REQUEST_DOCUMENTS",
			joinColumns=@JoinColumn(name="REQUEST_ID"),
			inverseJoinColumns=@JoinColumn(name="DOCUMENT_ID")
	)
	private List<Document> requiredDocuments;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="REQUESTED_DOCUMENT_TEMPLATE_ID", nullable=false, updatable=true, insertable=true)
	private DocumentTemplate requestedDocumentTemplate;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RESPONSE_ID", nullable=true)
	private Response response;
	
	@Convert(converter = HashMapConverterString.class)
    @Column(name = "COMPLETED_FIELDS_MAP", nullable = true)
	private Map<String, String> completedFieldsMap;

}
