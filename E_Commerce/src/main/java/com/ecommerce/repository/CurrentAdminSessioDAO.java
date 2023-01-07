package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.CurrentAdminSession;

@Repository
public interface CurrentAdminSessioDAO extends JpaRepository<CurrentAdminSession, Integer>{

	public CurrentAdminSession  findByUuid(String uuid);
	
}
