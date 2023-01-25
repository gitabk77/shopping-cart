package com.abhishek.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhishek.model.Page;

public interface PageRepo extends JpaRepository<Page, Integer> {
	
	Page findBySlug(String slug);
	
	/*
	 * @Query("select p from Page p where p.id != :id and p.slug = :slug") Page
	 * findBySlug(int id, String slug);
	 */
	Page findBySlugAndIdNot(String slug, int id);
	
	List<Page> findAllByOrderBySortingAsc();
}
