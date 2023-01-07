package com.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.CurrentAdminSession;
import com.ecommerce.entities.CurrentCustomerSession;
import com.ecommerce.entities.Customer;
import com.ecommerce.exceptions.CustomerException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.repository.CurrentAdminSessioDAO;
import com.ecommerce.repository.CurrentCustomerSessionDAO;
import com.ecommerce.repository.CustomerDAO;

@Service
public class CustomerServiceIMPL implements CustomerServices{

	@Autowired
	private CustomerDAO customerDao;
	
	@Autowired
	private CurrentCustomerSessionDAO customerSession;
	
	@Autowired
	private CurrentAdminSessioDAO adminSession;
	
	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub
		Customer existingCustomer = customerDao.findByMobile(customer.getMobile());
		
		if(existingCustomer!=null)
			throw new CustomerException("Customer already registered with mobile no. " + customer.getMobile());
		
		return customerDao.save(customer);
		
	}

	@Override
	public Customer updateCustomer(Customer customer, String uuid) throws CustomerException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentCustomerSession session = customerSession.findByUuid(uuid);
		
		if(session==null)
			throw new LoginException("No session found with uuid " + uuid);
		
		if(session.getUserId()!=customer.getCustomerId())
			throw new CustomerException("Invalid credentials.");
		
		return customerDao.save(customer);
	}

	@Override
	public Customer deleteCustomer(Integer customerId, String uuid) throws CustomerException, LoginException {
		// TODO Auto-generated method stub
		CurrentCustomerSession session = customerSession.findByUuid(uuid);
		
		if(session==null)
			throw new LoginException("No session found with uuid " + uuid);
		
		if(session.getUserId()!=customerId)
			throw new LoginException("Invalid uuid for customer with id " + customerId);
		
		Customer customer = customerDao.findById(customerId).orElseThrow(()-> new CustomerException("No customer found with id " + customerId));
		
		customerDao.delete(customer);
		
		return customer;
	}

	@Override
	public Customer getCustomerDetails(Integer customerId, String uuid) throws CustomerException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentCustomerSession session = customerSession.findByUuid(uuid);
		if(session==null)
			throw new LoginException("No session found with uuid " + uuid);
		
		if(session.getUserId()!=customerId)
			throw new LoginException("Invalid uuid for customer with id " + customerId);
		
		Customer customer = customerDao.findById(customerId).orElseThrow(()-> new CustomerException("No customer found with id " + customerId));
		
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers(String uuid) throws CustomerException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession session = adminSession.findByUuid(uuid);
		
		if(session==null)
			throw new LoginException("No admin session found with uuid " + uuid);
		
		List<Customer> customers = customerDao.findAll();
		
		if(customers.isEmpty())
			throw new CustomerException("No customers registered in the system yet.");
		
		return customers;
	}
	
}
