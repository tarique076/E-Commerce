package com.ecommerce.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.Admin;
import com.ecommerce.exceptions.AdminException;
import com.ecommerce.repository.AdminDAO;

@Service
public class AdminServicesIMPL implements AdminServices{

	@Autowired
	AdminDAO adminDao;
	
	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {
		
		Admin existingAdmin = adminDao.findByMobile(admin.getMobile());
		
		if(existingAdmin==null) {
			admin.setRegisteredDate(LocalDateTime.now());
			
			adminDao.save(admin);
			
			return admin;
		}else {
			throw new AdminException("Admin already registered with mobile no. " + admin.getMobile());
		}
	}

}
