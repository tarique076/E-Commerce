package com.ecommerce.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.Admin;
import com.ecommerce.entities.CurrentAdminSession;
import com.ecommerce.exceptions.AdminException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.repository.AdminDAO;
import com.ecommerce.repository.CurrentAdminSessioDAO;

@Service
public class AdminServicesIMPL implements AdminServices{

	@Autowired
	private AdminDAO adminDao;
	
	@Autowired
	private CurrentAdminSessioDAO sessionDao;
	
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

	@Override
	public Admin updateAdminDetails(Admin admin, String uuid) throws AdminException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession session = sessionDao.findByUuid(uuid);
		
		adminDao.findById(admin.getAdminId()).orElseThrow(() -> new AdminException("Invalid admin details."));
		
		if(session.getUserId()!=admin.getAdminId() || session==null) {
			throw new LoginException("Invalid uuid. No session found.");
		}
		
		return adminDao.save(admin);
	}

	@Override
	public Admin deleteAdmin(Integer adminId, String uuid) throws AdminException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession session = sessionDao.findByUuid(uuid);
		
		if(session==null || session.getUserId()!=adminId)
			throw new LoginException("Invalid uuid. User should be logged in.");
		
		Admin admin = adminDao.findById(adminId).orElseThrow(() -> new AdminException("No admin found with id " + adminId));
		
		adminDao.delete(admin);
		
		return admin;
	}

	@Override
	public Admin getAdminDetails(Integer adminId, String uuid) throws AdminException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession session = sessionDao.findByUuid(uuid);
		
		if(session==null || session.getUserId()!=adminId )
			throw new LoginException("Invalid uuid. User should be logged in.");
		
		Admin admin = adminDao.findById(adminId).orElseThrow(() -> new AdminException("No admin found with id " + adminId));
		
		return admin;
	}

}
