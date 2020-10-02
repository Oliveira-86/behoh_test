package com.behoh.testejavaweb.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "event_tb")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer vacancies;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateStart;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dateFinish;
	
	@ManyToMany(mappedBy = "events")
	private List<User> users = new ArrayList<>();

	public Event() {
	}

	public Event(Long id, String name, Integer vacancies, LocalDateTime dateStart, LocalDateTime dateFinish) {
		super();
		this.id = id;
		this.name = name;
		this.vacancies = vacancies;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		
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

	public Integer getVacancies() {
		return vacancies;
	}

	public void setVacancies(Integer vacancies) {
		this.vacancies = vacancies;
	}

	public LocalDateTime getdateStart() {
		return dateStart;
	}

	public void setdateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDateTime getdateFinish() {
		return dateFinish;
	}

	public void setdateFinish(LocalDateTime dateFinish) {
		this.dateFinish = dateFinish;
	}

	public List<User> getUsers() {
		return users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}