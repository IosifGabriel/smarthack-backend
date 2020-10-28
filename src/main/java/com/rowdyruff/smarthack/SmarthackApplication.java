package com.rowdyruff.smarthack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.rowdyruff.domain.User;
import com.rowdyruff.repository.UserRepository;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class SmarthackApplication {
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SmarthackApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	    userRepository.create(new User("foo", "foo"));
	}

}
