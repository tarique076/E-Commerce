package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

}
