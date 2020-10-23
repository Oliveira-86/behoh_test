package com.behoh.testejavaweb.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.behoh.testejavaweb.entities.Event;
import com.behoh.testejavaweb.entities.User;
import com.behoh.testejavaweb.entities.dto.EventDTO;
import com.behoh.testejavaweb.repositories.EventRepository;
import com.behoh.testejavaweb.services.exceptions.DataBaseException;
import com.behoh.testejavaweb.services.exceptions.EventNotFoundException;
import com.behoh.testejavaweb.services.utils.UserRegister;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	public List<Event> findAll() {
		return repository.findAll();
	}

	public Event findById(Long id) {
		Optional<Event> obj = repository.findById(id);
		return obj.orElseThrow(() -> new EventNotFoundException(id));
	}

	public Event insert(Event obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new EventNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public Event update(Long id, Event obj) {
		try {
			Event entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(obj);
		} 
		catch (EntityNotFoundException e) {
			throw new EventNotFoundException(id);
		}
	}
	
	public Event fromDto(EventDTO objDto) {
		return new Event(objDto.getId(), objDto.getName(), objDto.getVacancies(), objDto.getPrice(), objDto.getdateStart(),objDto.getdateFinish());
	}
	
	public void updateData(Event newObj, Event obj) {
		newObj.setName(obj.getName());
		newObj.setVacancies(obj.getVacancies());
		newObj.setdateStart(obj.getdateStart());
		newObj.setdateFinish(obj.getdateFinish());
	}
	
	public Page<Event> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public UserRegister register(Long eventId, User user) {
		try {
			Optional<Event> optinal = repository.findById(eventId);
			Event event = optinal.orElseThrow(() -> new EventNotFoundException(eventId));
			Integer vacancies = event.getVacancies();
			List<User> list = event.getUsers();
			LocalDateTime now = LocalDateTime.now();
			
			if (list.size() < vacancies && now.compareTo(event.getdateStart().plusHours(-1)) < 0) {
				event.getUsers().add(user);
				return new UserRegister("User register for the event!");
			}
			if (now.compareTo(event.getdateStart().plusHours(-1)) >= 0) {
				return new UserRegister("NOT REGISTERED. Expired registration date!");
			} 
			else {
				return new UserRegister("NOT REGISTERED. User limit reached!");
			}
		} 
		catch (EmptyResultDataAccessException e) {
			throw new EventNotFoundException(eventId);
		}

	}

	public UserRegister deregister(Long eventId, User user) {
		try {
			Optional<Event> optinal = repository.findById(eventId);
			Event event = optinal.orElseThrow(() -> new EventNotFoundException(eventId));
			LocalDateTime now = LocalDateTime.now();
			
			if (event.getUsers().contains(user) && now.isAfter(event.getdateStart())) {
				return new UserRegister(" CAN NOT BE CANCELED");
			}
			if (event.getUsers() != user){
				return  new UserRegister(" CAN NOT BE CANCELED. User is not at the event");
			}
			else {
				event.getUsers().remove(user);
				return new UserRegister("User deregister for the event!");
			}
		}
		catch(EmptyResultDataAccessException e) {
			throw new EventNotFoundException(eventId);
		}
		
	}
}
