package com.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.CustomerLoginDTO;
import com.ecommerce.exceptions.CustomerException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.services.CustomerLoginServices;

@RestController
public class CustomerLoginController {

	@Autowired
	private CustomerLoginServices customerLoginService;
	
	@PostMapping("/customer/login")
	public ResponseEntity<String> customerLogin(@Valid @RequestBody CustomerLoginDTO dto) throws LoginException, CustomerException{
		
		String res = customerLoginService.loginCustomer(dto);
		
		return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/customer/logout")
	public ResponseEntity<String> customerLogout(@RequestParam String uuid) throws LoginException, CustomerException{
		
		String res = customerLoginService.logoutCustomer(uuid);
		
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
}
