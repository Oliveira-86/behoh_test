package com.behoh.testejavaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.behoh.testejavaweb.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long	>{

}
