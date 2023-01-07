package com.ecommerce.services;

import com.ecommerce.entities.CustomerLoginDTO;
import com.ecommerce.exceptions.CustomerException;
import com.ecommerce.exceptions.LoginException;

public interface CustomerLoginServices {

	public String loginCustomer(CustomerLoginDTO dto) throws LoginException, CustomerException;
	
	public String logoutCustomer(String uuid) throws LoginException;
}
