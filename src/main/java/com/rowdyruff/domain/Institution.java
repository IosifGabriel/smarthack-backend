package com.rowdyruff.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rowdyruff.smarthack.model.InstitutionRequest;

import lombok.Data;

@Entity
@Data
@Table(name = "INSTITUTIONS")
public class Institution implements Serializable {

	private static final long serialVersionUID = -3227382696308050960L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "ABREVIATION", nullable = false)
	private String abreviation;
	
	@Column(name = "ADDRESS", nullable = false)
	private String address;
	
	public Institution(InstitutionRequest institution) {
		this.name = institution.getName();
		this.address = institution.getAddress();
		this.abreviation = institution.getAbreviation();
	}

	public Institution() {

	}
	
	
	
}
