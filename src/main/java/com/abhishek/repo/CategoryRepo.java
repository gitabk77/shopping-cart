package com.abhishek.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	Category findByName(String name);
	
	List<Category> findAllByOrderBySortingAsc();

	Category findBySlug(String slug);
}
