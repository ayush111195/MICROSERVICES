package com.user.services.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.user.services.entites.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	
	//GET
	
	//POST
	@PostMapping("/rating/")
	public Rating createRating( Rating values);
	
	//PUT
	@PutMapping("/rating/{ratingId}")
	public Rating updateRating(@PathVariable String ratingId , Rating rating);
	
	//DELETE
	@DeleteMapping("/rating/{ratingId}")
	public Rating deleteRating(@PathVariable String ratingId );
	
}
