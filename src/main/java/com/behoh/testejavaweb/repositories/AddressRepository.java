package com.behoh.testejavaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.behoh.testejavaweb.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
