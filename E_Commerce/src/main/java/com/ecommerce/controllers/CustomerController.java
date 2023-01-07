package com.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.Customer;
import com.ecommerce.exceptions.CustomerException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.services.CustomerServices;

@RestController
public class CustomerController {

	@Autowired
	private CustomerServices customerService;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) throws CustomerException{
		
		Customer registeredCustomer = customerService.addCustomer(customer);
		
		return new ResponseEntity<Customer>(registeredCustomer, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomerDetails(@Valid @RequestBody Customer customer, @RequestParam String uuid) throws CustomerException, LoginException{
		
		Customer updatedCustomer = customerService.updateCustomer(customer, uuid);
		
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Integer customerId, @RequestParam String uuid) throws CustomerException, LoginException{
		
		Customer deletedCustomer = customerService.deleteCustomer(customerId, uuid);
		
		return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable("id") Integer customerId, @RequestParam String uuid) throws CustomerException, LoginException{
		
		Customer customer = customerService.getCustomerDetails(customerId, uuid);
		
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
	}
	
	@GetMapping("/admin/customers")
	public ResponseEntity<List<Customer>> getCustomers(@RequestParam String uuid) throws CustomerException, LoginException{
		
		List<Customer> customers = customerService.getAllCustomers(uuid);
		
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
}
