package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{

	public Category findByCategoryName(String categoryName);
	
}
