package com.behoh.testejavaweb.entities.dto;

import java.io.Serializable;

import com.behoh.testejavaweb.entities.Event;

public class EventDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	public EventDTO() {
	}
	
	public EventDTO(Event obj) {
		id = obj.getId();
		name = obj.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
