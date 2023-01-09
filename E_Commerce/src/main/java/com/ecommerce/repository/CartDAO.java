package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.Cart;

public interface CartDAO extends JpaRepository<Cart, Integer>{

}
