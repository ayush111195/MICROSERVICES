package com.user.services.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.user.services.entites.Hotel;
import com.user.services.entites.Rating;
import com.user.services.entites.User;
import com.user.services.exceptions.ResourceNotFindException;
import com.user.services.repositories.UserRepository;
import com.user.services.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {
	
	@Autowired
	private  UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	

	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

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

		//get the user from database with the help of user repositry--->>>
	
		 User  user= this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFindException("User Is Given Id Is Not Found On Server!! :"+userId));
		 
		 // fetch rating of the above user from rating service---->>>
		 //http://localhost:8083/rating/user5ce623e3-85f4-417f-8f98-20dd8a7b3fbd
		 
	//	 Rating[] ratingOfUser = restTemplate.getForObject("http://localhost:8083/rating/user"+user.getUserId(),Rating[].class);
		 Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/user"+user.getUserId(),Rating[].class);
		 logger.info("{}",ratingOfUser);
		 
		List<Rating> ratings= Arrays.stream(ratingOfUser).toList();
		 
		 List<Rating>ratingList=ratings.stream().map(rating ->{
			//api call to hotel services to get the hotel---->>>
			// http://localhost:8082/hotels/c59f8b0b-9eca-43d8-bad9-8b2ced5a2491
			 System.out.println(rating.getHotelId());
	//	ResponseEntity<Hotel> forEntity=	 restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(),Hotel.class);
		ResponseEntity<Hotel> forEntity=	 restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
		  Hotel hotel=forEntity.getBody();
		  logger.info("responce status code:{}",forEntity.getStatusCode());
		  
		 // set the hotel to rating --->>
		  
		  rating.setHotel(hotel);
		 
		  //return the rating--->>
		  
		  return rating;
		  
		 }).collect(Collectors.toList());
		 
		 user.setRatings(ratingList);
		  return user;
		
	
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
