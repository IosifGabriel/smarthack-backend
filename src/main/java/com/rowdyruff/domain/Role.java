package com.rowdyruff.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
		ROLE_SUPERVISOR,
		ROLE_INSTITUTION_ADMIN,
		ROLE_INSTITUTION_USER,
		ROLE_CLIENT;

	@Override
	public String getAuthority() {
		return name();
	}
	
	

}
