package com.rowdyruff.smarthack.model;

import lombok.Data;

@Data
public class CreateUserRequest {
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String cnp;
	
	private String role;
	
	private Integer institutionId;

}
