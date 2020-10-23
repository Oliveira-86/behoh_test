package com.behoh.testejavaweb.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.behoh.testejavaweb.entities.Event;
import com.behoh.testejavaweb.entities.User;
import com.behoh.testejavaweb.entities.dto.EventDTO;
import com.behoh.testejavaweb.resource.utils.URL;
import com.behoh.testejavaweb.services.EventService;
import com.behoh.testejavaweb.services.utils.UserRegister;

@RestController
@RequestMapping(value = "/events")
public class EventResource {

	@Autowired
	private EventService service; 
	
	@GetMapping
	public ResponseEntity<List<EventDTO>> findAll() {
		List<Event> list = service.findAll();
		List<EventDTO> listDto = list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Event> findById(@PathVariable Long id) {
		Event obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO objDto) {
		Event obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id ) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody Event obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<EventDTO>> findPage(
			@RequestParam(value="name", defaultValue="") String name, 
			@RequestParam(value="categories", defaultValue="") String categories, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nameDecoded = URL.decodeParam(name);
		List<Long> ids = URL.decodeIntList(categories);
		Page<Event> list = service.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<EventDTO> listDto = list.map(obj -> new EventDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping(value = "/{eventId}/register")
	public ResponseEntity<UserRegister> register(@PathVariable Long eventId, @RequestBody User user) {
			UserRegister register = service.register(eventId, user);
			return ResponseEntity.ok().body(register);
	}
	
	@DeleteMapping(value = "/{eventId}/deregister/")
	public ResponseEntity<UserRegister> deregister(@PathVariable Long eventId, @RequestBody User user) {
			UserRegister response = service.deregister(eventId, user);
			return ResponseEntity.ok().body(response);
	}
}
