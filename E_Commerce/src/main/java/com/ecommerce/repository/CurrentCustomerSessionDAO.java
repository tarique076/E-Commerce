package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.CurrentCustomerSession;

@Repository
public interface CurrentCustomerSessionDAO extends JpaRepository<CurrentCustomerSession, Integer>{

	public CurrentCustomerSession findByUuid(String uuid);
	
}
