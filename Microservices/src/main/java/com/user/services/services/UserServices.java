package com.user.services.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.services.entites.User;


public interface UserServices {

// user oprations--->>
	
	// create ----->>
	
	User saveUser(User user);
	
	//get all user---->>
	
	List<User> getAllUser();
	
	//  get user by id---->>
	
	User getById( String userId);
	
	// dlt user by id --->>
	
	  void dltById( String userId);
	
	// update the user--->>
	
	User updateUser(User user , String userId);	
}
