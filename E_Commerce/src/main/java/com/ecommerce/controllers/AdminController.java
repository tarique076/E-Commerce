package com.ecommerce.controllers;

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

import com.ecommerce.entities.Admin;
import com.ecommerce.exceptions.AdminException;
import com.ecommerce.exceptions.LoginException;
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
	
	@PutMapping("/admin")
	public ResponseEntity<Admin> updateAdminDetails(@Valid @RequestBody Admin admin, @RequestParam String uuid) throws AdminException, LoginException{
		
		Admin updatedAdmin = adminService.updateAdminDetails(admin, uuid);
		
		return new ResponseEntity<Admin>(updatedAdmin, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/admin/{adminId}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable("adminId") Integer adminId, @RequestParam String uuid) throws AdminException, LoginException{
		
		Admin deletedAdmin = adminService.deleteAdmin(adminId, uuid);
		
		return new ResponseEntity<Admin>(deletedAdmin, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/admin/{adminId}")
	public ResponseEntity<Admin> getAdminDetails(@PathVariable("adminId") Integer adminId, @RequestParam String uuid) throws AdminException, LoginException{
		
		Admin admin = adminService.getAdminDetails(adminId, uuid);
		
		return new ResponseEntity<Admin>(admin,HttpStatus.OK);
		
	}
}
