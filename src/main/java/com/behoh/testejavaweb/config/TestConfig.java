package com.behoh.testejavaweb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.behoh.testejavaweb.entities.Usuario;
import com.behoh.testejavaweb.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	
	@Autowired
	private UsuarioRepository usuRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario usu1 = new Usuario(null, "Manoel Duarte");
		Usuario usu2 = new Usuario(null, "Maria do Carmo");
		
		usuRepository.saveAll(Arrays.asList(usu1, usu2));
		
	}

}
