package com.behoh.testejavaweb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.behoh.testejavaweb.entities.Evento;
import com.behoh.testejavaweb.entities.Usuario;
import com.behoh.testejavaweb.repositories.EventoRepository;
import com.behoh.testejavaweb.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	
	@Autowired
	private UsuarioRepository usuRepository;
	
	@Autowired
	private EventoRepository eveRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Usuario usu1 = new Usuario(null, "Manoel Duarte");
		Usuario usu2 = new Usuario(null, "Maria do Carmo");
		
		Evento eve1 = new Evento(null, "cinema", 45, sdf.parse("31/11/2020 18:00"), sdf.parse("31/11/2020 20:00"));
		Evento eve2 = new Evento(null, "Jantar", 5, sdf.parse("29/11/2021 19:30"), sdf.parse("29/11/2020 22:45"));
		Evento eve3 = new Evento(null, "Viagem", 10, sdf.parse("24/11/2020 09:00"), sdf.parse("27/11/2020 14:15"));
		Evento eve4 = new Evento(null, "Aula", 45, sdf.parse("02/09/2020 07:15"), sdf.parse("02/09/2020 12:30"));
		
		usuRepository.saveAll(Arrays.asList(usu1, usu2));
		eveRepository.saveAll(Arrays.asList(eve1, eve2, eve3,eve4));
	}

}
