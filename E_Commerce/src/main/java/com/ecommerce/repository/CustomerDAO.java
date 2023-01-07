package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer>{

	public Customer findByMobile(String mobile);
}
