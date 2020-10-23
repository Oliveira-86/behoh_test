package com.behoh.testejavaweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.behoh.testejavaweb.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
