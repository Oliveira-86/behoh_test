package com.behoh.testejavaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.behoh.testejavaweb.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{

}
