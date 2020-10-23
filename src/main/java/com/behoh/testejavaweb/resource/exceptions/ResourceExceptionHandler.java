package com.behoh.testejavaweb.resource.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.behoh.testejavaweb.services.exceptions.CategoryNotFoundException;
import com.behoh.testejavaweb.services.exceptions.DataBaseException;
import com.behoh.testejavaweb.services.exceptions.EventNotFoundException;
import com.behoh.testejavaweb.services.exceptions.UserNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(EventNotFoundException.class)
	public ResponseEntity<StandardError> EventNotFound(EventNotFoundException e, HttpServletRequest request){
		String error = "Event not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<StandardError> UserNotFound(UserNotFoundException e, HttpServletRequest request){
		String error = "User not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<StandardError> UserNotFound(CategoryNotFoundException e, HttpServletRequest request){
		String error = "Category not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> resourceNotFound(DataBaseException e, HttpServletRequest request){
		String error = "Database Error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		String error = "Validation Error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError err = new ValidationError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			 err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}
	
}
