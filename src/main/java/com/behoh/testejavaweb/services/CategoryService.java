package com.behoh.testejavaweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.behoh.testejavaweb.entities.Category;
import com.behoh.testejavaweb.entities.dto.CategoryDTO;
import com.behoh.testejavaweb.repositories.CategoryRepository;
import com.behoh.testejavaweb.services.exceptions.CategoryNotFoundException;
import com.behoh.testejavaweb.services.exceptions.DataBaseException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		List<Category> list = repository.findAll();
		return list;
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new CategoryNotFoundException(id));
	}

	public Category insert(Category obj) {
		return repository.save(obj);
	}

	public Category update(Category obj) {
		Category newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id); 
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataBaseException("Is not possible delete Category with Product");
		}
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName());
	}
	
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}