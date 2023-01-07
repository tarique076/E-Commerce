package com.ecommerce.services;

import com.ecommerce.entities.Admin;
import com.ecommerce.exceptions.AdminException;

public interface AdminServices {

	public Admin registerAdmin(Admin admin) throws AdminException;
}
