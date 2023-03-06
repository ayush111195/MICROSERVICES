package com.user.services.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;



import com.user.services.entites.User;
import com.user.services.exceptions.ResourceNotFindException;
import com.user.services.repositories.UserRepository;
import com.user.services.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {
	
	@Autowired
	private  UserRepository userRepository;

	@Override
	public User saveUser(User user) {
	
		String rendomUserId = UUID.randomUUID().toString();
		
		//generated unique id --->>
		
		user.setUserId(rendomUserId);
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {

		
		 Iterable<User> findAll = this.userRepository.findAll();
		 
		 return (List<User>) findAll;
	}

	@Override
	public User getById(String userId) {

	
		return this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFindException("User Is Given Id Is Not Found On Server!! :"+userId));
	}

	@Override
	public void dltById(String userId) {
		
		 User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceAccessException("User Is Given Id Is Not Founf On Server!!:"+ userId));
		
		  this.userRepository.delete(user);
	}

	@Override
	public User updateUser(User user, String userId) {
		
		
		User user2 = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFindException("User Is Given Id Is Not Founf On Server!!:"+ userId));
	
		
		return this.userRepository.save(user2);
		
		
		 
	}

}
