package com.behoh.testejavaweb.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.behoh.testejavaweb.entities.Category;
import com.behoh.testejavaweb.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Event obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
	Page<Event> findDistinctByNomeContainingAndCategoriasIn(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest);

}
