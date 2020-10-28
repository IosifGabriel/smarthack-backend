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
@Table(name = "TODOS")
public class ToDo implements Serializable {

	private static final long serialVersionUID = -4318956959985340888L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "TITLE", nullable = false)
	private String title;

	public ToDo(String title) {
		this.title = title;
	}
	
	public ToDo() {

	}
	
	
}
