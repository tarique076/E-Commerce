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

import com.ecommerce.entities.AdminLoginDTO;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.services.AdminLoginServices;

@RestController
public class AdminLoginController {

	@Autowired
	AdminLoginServices adminLoginService;
	
	@PostMapping("/admin/login")
	public ResponseEntity<String> loginAdminHandler(@Valid @RequestBody AdminLoginDTO dto ) throws LoginException{
		
		String response = adminLoginService.adminLogin(dto);
		
		return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/admin/logout")
	public ResponseEntity<String> logoutAdminHandler(@RequestParam String uuid) throws LoginException{
		
		String response = adminLoginService.adminLogout(uuid);
		
		return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}
}
