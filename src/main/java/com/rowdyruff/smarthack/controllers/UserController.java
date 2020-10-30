package com.rowdyruff.smarthack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rowdyruff.domain.User;
import com.rowdyruff.repository.UserRepository;
import com.rowdyruff.smarthack.model.AuthenticationRequest;
import com.rowdyruff.smarthack.model.AuthenticationResponse;
import com.rowdyruff.smarthack.model.RegisterForm;
import com.rowdyruff.smarthack.service.MyUserDetailsService;
import com.rowdyruff.smarthack.utils.JwtUtils;

@RestController
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtils jwtTokenUtil;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String firstPage() {
		return "Hello world";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody RegisterForm registerForm) {
		return ResponseEntity.ok(userRepository.create(new User(registerForm.getUsername(), registerForm.getPassword())));
	}
	
}
