package com.rowdyruff.domain;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "RESPONSES")
public class Response implements Serializable {

	private static final long serialVersionUID = -1340631058276135663L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REQUEST_ID", nullable=false)
	private Request request;
    
    @ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="CLERK_ID", nullable=false, updatable=true, insertable=true)
    private User clerk;
    
    @Enumerated(EnumType.STRING)
	private RequestStatus requestStatus;
    
    @Convert(converter = HashMapConverter.class)
    @Column(name = "DOCUMENT_STATUSES")
    private Map<Integer, String> documentStatuses;

}
