package com.rowdyruff.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name = "DOCUMENTS")
public class Document implements Serializable {

	
	private static final long serialVersionUID = 8158310758256378390L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name="RELEASE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseDate;
	
	@Column(name="EXPIRY_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationDate;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="INSTITUTION_ID", nullable=false, updatable=true, insertable=true)
	private Institution institution;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="USER_ID", nullable=false, updatable=true, insertable=true)
	private User ownerUser;
	
	@ManyToOne(cascade= {}, fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="REQUEST_ID", nullable=false, updatable=true, insertable=true)
	private Request request;
	
	private DocumentTemplate template;

}