package com.behoh.testejavaweb.services.exceptions;

public class CategoryNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CategoryNotFoundException(Object id) {
		super ("Category not found. Id " + id);
	}

}