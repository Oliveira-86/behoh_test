package com.behoh.testejavaweb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.behoh.testejavaweb.entities.Event;
import com.behoh.testejavaweb.entities.User;
import com.behoh.testejavaweb.repositories.EventRepository;
import com.behoh.testejavaweb.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig {

	
	@Autowired
	private UserRepository usuRepository;
	
	@Autowired
	private EventRepository eveRepository;
	
	
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		User usu1 = new User(null, "Manoel Duarte");
		User usu2 = new User(null, "Maria do Carmo");
		User usu3 = new User(null, "Elis Mariana");
		User usu4 = new User(null, "Ana Alves");
		User usu5 = new User(null, "Alfredo Ricardo");
		User usu6 = new User(null, "Anita leocadia");
		User usu7 = new User(null, "Vinicius Morais");
		User usu8 = new User(null, "Laura Tiné");
		User usu9 = new User(null, "Jéssica Vasconcelos");
		User usu10 = new User(null, "Miguel Duarte");
		
		/*
		Event eve1 = new Event(null, "cinema", 45, sdf.parse("31/11/2020 18:00"), sdf.parse("31/11/2020 20:00"));
		Event eve2 = new Event(null, "Jantar", 5, sdf.parse("29/11/2021 19:30"), sdf.parse("29/11/2020 22:45"));
		Event eve3 = new Event(null, "Viagem", 10, sdf.parse("24/11/2020 09:00"), sdf.parse("27/11/2020 14:15"));
		Event eve4 = new Event(null, "Aula", 45, sdf.parse("02/09/2020 07:15"), sdf.parse("02/09/2020 12:30"));
		
		
		usuRepository.saveAll(Arrays.asList(usu1, usu2, usu3, usu4, usu5, usu6, usu7, usu8, usu9, usu10));
		eveRepository.saveAll(Arrays.asList(eve1, eve2, eve3, eve4));
		
		usu1.getEvents().addAll(Arrays.asList(eve1, eve2));
		
		usuRepository.save(usu1);
		*/
		
		
	}

}
