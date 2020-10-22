package com.behoh.testejavaweb.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.behoh.testejavaweb.entities.User;
import com.behoh.testejavaweb.entities.dto.UserDTO;
import com.behoh.testejavaweb.repositories.UserRepository;
import com.behoh.testejavaweb.services.exceptions.DataBaseException;
import com.behoh.testejavaweb.services.exceptions.UserNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
		
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new UserNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getOne(id);
			updateData(obj, entity);
			return repository.save(obj);
		}
		catch(EntityNotFoundException e) {
			throw new UserNotFoundException(id);
		}
	}
	
	public void delete(Long id) {
		try {
			findById(id);
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new UserNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
	}
	
	public User fromDto(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName());
	}
}
