package com.behoh.testejavaweb.resource.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.behoh.testejavaweb.services.exceptions.DataBaseException;
import com.behoh.testejavaweb.services.exceptions.EventNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(EventNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(EventNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
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
	
	
}
