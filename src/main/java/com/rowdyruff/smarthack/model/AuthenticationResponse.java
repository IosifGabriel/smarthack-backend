package com.rowdyruff.smarthack.model;

import com.rowdyruff.domain.User;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private User user;
	
	private String jwt;

	public AuthenticationResponse(User user, String jwt) {
		this.user = user;
		this.jwt = jwt;
	}
	
	
}

