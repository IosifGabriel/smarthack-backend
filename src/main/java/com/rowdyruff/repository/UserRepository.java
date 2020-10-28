package com.rowdyruff.repository;

import org.springframework.stereotype.Repository;

import com.rowdyruff.domain.User;

public interface UserRepository extends GenericRepository<User> {

	User findByUsername(String username);
}
