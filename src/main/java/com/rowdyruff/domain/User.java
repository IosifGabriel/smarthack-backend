package com.rowdyruff.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = -5476517849040239786L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "USERNAME", nullable = false)
	private String username;
	
	@Column(name = "FIRST_NAME", nullable = true)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = true)
	private String lastName;
	
	@Column(name = "EMAIL", nullable = true)
	private String email;
	
	@Column(name = "CNP", nullable = true)
	private String cnp;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToOne(cascade={}, fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="INSTITUTION_ID", nullable=true, updatable=true, insertable=true)
	private Institution institution;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	public User(String cnp, String password) {
		this.cnp = cnp;
		this.username = cnp;
		this.password =  password;
	}

	public User() {

	}
	
	
	
	
}
