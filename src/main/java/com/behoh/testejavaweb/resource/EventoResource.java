package com.behoh.testejavaweb.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.behoh.testejavaweb.entities.Evento;
import com.behoh.testejavaweb.services.EventoService;

@RestController
@RequestMapping(value = "/eventos")
public class EventoResource {

	@Autowired
	private EventoService service; 
	
	@GetMapping
	public ResponseEntity<List<Evento>> findAll() {
		List<Evento> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Evento> findById(@PathVariable Long id) {
		Evento obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
