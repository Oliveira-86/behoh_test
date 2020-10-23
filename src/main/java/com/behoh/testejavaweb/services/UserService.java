package com.behoh.testejavaweb.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.behoh.testejavaweb.entities.Address;
import com.behoh.testejavaweb.entities.City;
import com.behoh.testejavaweb.entities.User;
import com.behoh.testejavaweb.entities.dto.UserDTO;
import com.behoh.testejavaweb.entities.dto.UserNewDTO;
import com.behoh.testejavaweb.entities.enums.TypeUser;
import com.behoh.testejavaweb.repositories.AddressRepository;
import com.behoh.testejavaweb.repositories.UserRepository;
import com.behoh.testejavaweb.services.exceptions.DataBaseException;
import com.behoh.testejavaweb.services.exceptions.UserNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AddressRepository addRepository;
	
	public List<User> findAll() {
		return repository.findAll();
		
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new UserNotFoundException(id));
	}
	
	public User insert(User obj) {
		obj = repository.save(obj);
		addRepository.saveAll(obj.getAdrresses());
		return obj;
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
		newObj.setEmail(obj.getEmail());
		newObj.setCpfOuCnpj(obj.getCpfOuCnpj());
	}
	
	public User fromDto(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	public User fromDto(UserNewDTO objDto) {
		User user = new User(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOuCnpj(), TypeUser.toEnum(objDto.getType()));
		City city = new City(objDto.getCityId(), null, null);
		Address add = new Address(null, objDto.getPublicPlace(), objDto.getNumber(), objDto.getComplement(), objDto.getNeighborhood(), objDto.getZipCode(), user, city);
		user.getAdrresses().add(add);
		user.getPhone().add(objDto.getPhone());
		if(objDto.getPhone2()!=null) {
			user.getPhone().add(objDto.getPhone2());
		}
		if(objDto.getPhone3()!=null) {
			user.getPhone().add(objDto.getPhone3());
		}
		return user;
	}
}
