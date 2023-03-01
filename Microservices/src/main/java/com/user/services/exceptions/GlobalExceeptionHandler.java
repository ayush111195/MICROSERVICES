package com.user.services.exceptions;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.services.payload.ApiResponce;

@RestControllerAdvice
public class GlobalExceeptionHandler {

	@ExceptionHandler(ResourceNotFindException.class)
	public ResponseEntity<ApiResponce> handlerResourceNotFoundException(ResourceNotFindException ex){
		
		String message = ex.getMessage();
		ApiResponce responce = ApiResponce.builder().message(message).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponce>(responce,HttpStatus.NOT_FOUND);
		
	}
}
