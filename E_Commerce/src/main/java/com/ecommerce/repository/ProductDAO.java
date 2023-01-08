package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

	public List<Product> findByProductNameContainingIgnoreCase(String name);
	
	public List<Product> findByCategory(String category);
	
	public List<Product> findByOrderByRatingDesc();
	
	public List<Product> findByRatingBetween(Double min, Double max);
	
	public List<Product> findByOrderByPriceAsc();
	
	public List<Product> findByOrderByPriceDesc();
	
	public List<Product> findByPriceBetween(Double min, Double max);
}
