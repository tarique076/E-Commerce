package com.ecommerce.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.Admin;
import com.ecommerce.entities.AdminLoginDTO;
import com.ecommerce.entities.CurrentAdminSession;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.repository.AdminDAO;
import com.ecommerce.repository.CurrentAdminSessioDAO;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginServicesIMPL implements AdminLoginServices{

	@Autowired
	AdminDAO adminDao;
	
	@Autowired
	CurrentAdminSessioDAO adminSession;
	
	@Override
	public String adminLogin(AdminLoginDTO dto) throws LoginException {
		
		Admin admin = adminDao.findByMobile(dto.getMobile());
		
		if(admin==null) {
			throw new LoginException("Invalid credentials. Please try again..");
		}
		else {
			
			Optional<CurrentAdminSession> currSessionOpt = adminSession.findById(admin.getAdminId());
			
			if(currSessionOpt.isPresent()) {
				throw new LoginException("Admin already logged in");
			}
			
			if(admin.getPassword().equals(dto.getPassword())) {
				
				String uuid = RandomString.make(6);
				
				CurrentAdminSession session = new CurrentAdminSession(admin.getAdminId(), uuid, LocalDateTime.now());
				
				adminSession.save(session);
				
				return session.toString();
			}else {
				throw new LoginException("Invalid password.");
			}
			
		}
		
	}

	@Override
	public String adminLogout(String uuid) throws LoginException {
		
		CurrentAdminSession session = adminSession.findByUuid(uuid);
		
		if(session==null) {
			throw new LoginException("No user logged in with key "+uuid);
		}
		
		adminSession.delete(session);
		
		return "Logged Out.!";
		
	}

}
