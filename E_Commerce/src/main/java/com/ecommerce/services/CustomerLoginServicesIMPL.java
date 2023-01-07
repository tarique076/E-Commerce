package com.ecommerce.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.CurrentCustomerSession;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.CustomerLoginDTO;
import com.ecommerce.exceptions.CustomerException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.repository.CurrentCustomerSessionDAO;
import com.ecommerce.repository.CustomerDAO;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerLoginServicesIMPL implements CustomerLoginServices{

	@Autowired
	private CurrentCustomerSessionDAO sessionDao;
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Override
	public String loginCustomer(CustomerLoginDTO dto) throws LoginException, CustomerException {
		// TODO Auto-generated method stub
		
		Customer customer = customerDao.findByMobile(dto.getMobile());
		
		if(customer==null)
			throw new CustomerException("No customer found with mobile " + dto.getMobile());
		
		Optional<CurrentCustomerSession> opt = sessionDao.findById(customer.getCustomerId());
		
		if(opt.isPresent())
			new LoginException("User already logged in");
		
		if(customer.getPassword().equals(dto.getPassword())) {
			
			String uuid = RandomString.make(6);
			
			CurrentCustomerSession session = new CurrentCustomerSession(customer.getCustomerId(), uuid, LocalDateTime.now());
			
			sessionDao.save(session);
			
			return session.toString();
		}else
			throw new LoginException("Invalid password. Please try again.");
		
		
	}

	@Override
	public String logoutCustomer(String uuid) throws LoginException {
		// TODO Auto-generated method stub
		
		CurrentCustomerSession session = sessionDao.findByUuid(uuid);
		
		if(session==null)
			throw new LoginException("No session found with id " + uuid);
		
		sessionDao.delete(session);
		
		return "Logged Out.!";
	}

}
