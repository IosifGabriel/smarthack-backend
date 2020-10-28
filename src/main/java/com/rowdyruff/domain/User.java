package com.rowdyruff.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = -5476517849040239786L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User() {

	}
	
	
	
	
}
