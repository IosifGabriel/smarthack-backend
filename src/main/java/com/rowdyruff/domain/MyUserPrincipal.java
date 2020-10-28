package com.rowdyruff.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

public class MyUserPrincipal implements UserDetails {

	private static final long serialVersionUID = 7719588752715042623L;
	
	@Getter
	private User user;
	
	public MyUserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        for (final Privilege privilege : user.getPrivileges()) {
//            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
//        }
//        return authorities;
		
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
