package com.behoh.testejavaweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behoh.testejavaweb.entities.Evento;
import com.behoh.testejavaweb.repositories.EventoRepository;

@Service
public class EventoService {
	
	@Autowired
	private EventoRepository repository;
	
	public List<Evento> findAll() {
		return repository.findAll();
		
	}
	
	public Evento findById(Long id) {
		Optional<Evento> obj = repository.findById(id);
		return obj.get();
	}
}
