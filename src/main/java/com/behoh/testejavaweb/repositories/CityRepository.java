package com.behoh.testejavaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.behoh.testejavaweb.entities.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
