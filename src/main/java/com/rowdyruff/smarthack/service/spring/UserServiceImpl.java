package com.rowdyruff.smarthack.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rowdyruff.domain.User;
import com.rowdyruff.repository.UserRepository;
import com.rowdyruff.smarthack.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
	
	UserRepository UserRepository;

	@Autowired
	public UserServiceImpl(UserRepository UserRepository) {
		super.setRepository(UserRepository);
		this.UserRepository = UserRepository;
	}
	
}
