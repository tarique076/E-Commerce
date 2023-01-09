package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.CartProductDTO;

public interface CartProductDtoDAO extends JpaRepository<CartProductDTO, Integer>{

}
