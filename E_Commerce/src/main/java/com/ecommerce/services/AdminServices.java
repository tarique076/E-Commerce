package com.ecommerce.services;

import com.ecommerce.entities.Admin;
import com.ecommerce.exceptions.AdminException;
import com.ecommerce.exceptions.LoginException;

public interface AdminServices {

	public Admin registerAdmin(Admin admin) throws AdminException;

	public Admin updateAdminDetails(Admin admin, String uuid) throws AdminException, LoginException;
	
	public Admin deleteAdmin(Integer adminId, String uuid) throws AdminException, LoginException;
	
	public Admin getAdminDetails(Integer adminId, String uuid) throws AdminException, LoginException;
}
