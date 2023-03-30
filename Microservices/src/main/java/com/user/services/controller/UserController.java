package com.user.services.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.services.entites.User;
import com.user.services.services.UserServices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.Builder;

@RestController
@RequestMapping("/users")

public class UserController {

	@Autowired
	private UserServices userServices;
	
	private Logger logger=LoggerFactory.getLogger(UserController.class);

	// create ----->>

	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User saveUser = userServices.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);

	}

	// get user by id---->>

	@GetMapping("/{userId}")
	@CircuitBreaker(name="RATING_HOTEL_BREAKER",fallbackMethod = "RATING_HOTEL_FALLBACK")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
		User byId = userServices.getById(userId);
		return ResponseEntity.ok(byId);
	}
	
	//CREATING FALL BACK METHOD FOR  CIRCUITBREAKER---->>>>
	
	public ResponseEntity<User>RATING_HOTEL_FALLBACK(String userId,Exception ex){
		
		logger.info("FALLBACK IS EXECUTED BECAUSE SERVICE IS DOWN :" , ex.getMessage());
	
		User user=User.builder()
				.email("AYUSSH")
				.name("AYUSSH")
				.about("AYUSSH")
				.userId("AYUSSH")
				.build();
		
	   return new ResponseEntity<>(user,HttpStatus.OK);
		
	    
		
	}

	// get all user---->>

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {

		List<User> allUser = userServices.getAllUser();
		return ResponseEntity.ok(allUser);

	}
	
	// dlt user by id --->>
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<User> dltUser(@PathVariable  String userId){
	 userServices.dltById(userId);
	 return ResponseEntity.accepted().build();
	 
	
	}
	
	// update the user--->>
	
	@PostMapping("/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId){
		User updateUser = userServices.updateUser(user, userId);
		return ResponseEntity.ok(updateUser);
		
	}
}

