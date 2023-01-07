package com.ecommerce.services;

import com.ecommerce.entities.AdminLoginDTO;
import com.ecommerce.exceptions.LoginException;

public interface AdminLoginServices {

	public String adminLogin(AdminLoginDTO dto) throws LoginException;
	
	public String adminLogout(String uuid) throws LoginException;
}
