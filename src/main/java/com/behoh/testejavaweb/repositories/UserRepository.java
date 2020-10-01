package com.behoh.testejavaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.behoh.testejavaweb.entities.User;

public interface UserRepository extends JpaRepository<User, Long	>{

}
