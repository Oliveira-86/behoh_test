package com.behoh.testejavaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.behoh.testejavaweb.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}
