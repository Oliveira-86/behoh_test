package com.behoh.testejavaweb.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behoh.testejavaweb.entities.Event;
import com.behoh.testejavaweb.entities.User;
import com.behoh.testejavaweb.repositories.EventRepository;
import com.behoh.testejavaweb.services.exceptions.ResourceNotFoundException;
import com.behoh.testejavaweb.services.utils.UserRegister;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	public EventService (EventRepository repository) {
		this.repository = repository;
	}
	
	public List<Event> findAll() {
		return repository.findAll();	
	}
	
	public Event findById(Long id) {
		Optional<Event> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Event insert(Event obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Event update(Long id, Event obj) {
		findById(obj.getId());
		return repository.save(obj);
	}
	
	public UserRegister register(Long id, User obj) {
		Optional<Event> optinal = repository.findById(id);
		Event event = optinal.orElseThrow(() -> new ResourceNotFoundException(id));
		Integer vacancies = event.getVacancies();
		List<User> list = event.getUsers();
		LocalDateTime now = LocalDateTime.now();
		
		if(list.size() < vacancies) {
			event.getUsers().add(obj);
			return new UserRegister("User register for the event!");
		}
		if (now.compareTo(event.getdateStart().plusHours(-1)) >= 0 )  {
			return new UserRegister("NOT REGISTERED. Expired registration date!");
		}
		else {
			return new UserRegister("NOT REGISTERED. User limit reached!");
		}
	}

	
	public UserRegister deregister(Long id, User obj) {
		Optional<Event> optinal = repository.findById(id);
		Event event = optinal.orElseThrow(() -> new ResourceNotFoundException(id));
		LocalDateTime now = LocalDateTime.now();
		
		if (event.getUsers().contains(obj) && now.isAfter(event.getdateStart())) {
			return new UserRegister(" CAN NOT BE CANCELED");
		}
		else {
			event.getUsers().remove(obj);
			return new UserRegister("User deregister for the event!");
		}
	}
	
}
