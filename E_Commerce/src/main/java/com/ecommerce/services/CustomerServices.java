package com.ecommerce.services;

import java.util.List;

import com.ecommerce.entities.Customer;
import com.ecommerce.exceptions.CustomerException;
import com.ecommerce.exceptions.LoginException;

public interface CustomerServices {

	public Customer addCustomer(Customer customer) throws CustomerException;
	
	public Customer updateCustomer(Customer customer, String uuid) throws CustomerException, LoginException;
	
	public Customer deleteCustomer(Integer customerId, String uuid) throws CustomerException, LoginException;

	public Customer getCustomerDetails(Integer customerId, String uuid) throws CustomerException, LoginException;

	public List<Customer> getAllCustomers(String uuid) throws CustomerException, LoginException;

}
