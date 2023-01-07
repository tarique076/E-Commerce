package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.CurrentAdminSession;

public interface CurrentAdminSessioDAO extends JpaRepository<CurrentAdminSession, Integer>{

	public CurrentAdminSession  findByUuid(String uuid);
	
}
