package com.behoh.testejavaweb.services.exceptions;

public class EventNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EventNotFoundException(Object id) {
		super ("Event not found. Id " + id);
	}

}