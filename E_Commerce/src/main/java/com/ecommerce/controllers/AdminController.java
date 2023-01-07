package com.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.Admin;
import com.ecommerce.exceptions.AdminException;
import com.ecommerce.services.AdminServices;

@RestController
public class AdminController {

	@Autowired
	AdminServices adminService;
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin) throws AdminException{
		
		Admin saveAdmin = adminService.registerAdmin(admin);
		
		return new ResponseEntity<Admin>(saveAdmin, HttpStatus.ACCEPTED);
	}
}
