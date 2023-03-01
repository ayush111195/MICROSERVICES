package com.user.services.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.user.services.entites.User;
import com.user.services.exceptions.ResourceNotFindException;
import com.user.services.repositories.UserRepository;
import com.user.services.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {
	
	private  UserRepository userRepository;

	@Override
	public User saveUser(User user) {
	
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {

		return this.userRepository.findAll();
	}

	@Override
	public User getById(String userId) {

	
		return this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFindException("User Is Given Id Is Not Found On Server!! :"+userId));
	}

	@Override
	public User dltById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
