package com.user.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.user.services.entites.User;
import com.user.services.repositories.UserRepository;

@SpringBootApplication
public class MicroservicesApplication {

	public static void main(String[] args) {
			SpringApplication.run(MicroservicesApplication.class, args);
	
	}
	
	

}
