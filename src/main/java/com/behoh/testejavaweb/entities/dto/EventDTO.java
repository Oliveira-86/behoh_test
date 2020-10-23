package com.behoh.testejavaweb.entities.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.behoh.testejavaweb.entities.Event;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EventDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Integer vacancies;
	private Double price;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateStart;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateFinish;
	
	public EventDTO() {
	}
	
	public EventDTO(Event obj) {
		id = obj.getId();
		name = obj.getName();
		dateStart = obj.getdateStart();
		dateFinish = obj.getdateFinish();
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
	
	public LocalDateTime getdateStart() {
		return dateStart;
	}
	
	public void setdateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	public Integer getVacancies() {
		return vacancies;
	}
	
	public LocalDateTime getdateFinish() {
		return dateFinish;
	}
	
	public void getdateFinish(LocalDateTime dateFinish) {
		this.dateFinish = dateFinish;
	}

	public void setVacancies(Integer vacancies) {
		this.vacancies = vacancies;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
