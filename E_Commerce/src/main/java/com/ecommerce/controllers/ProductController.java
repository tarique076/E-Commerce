package com.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.Product;
import com.ecommerce.exceptions.CategoryException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.services.ProductServices;

@RestController
public class ProductController {

	@Autowired
	private ProductServices productService;
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product, @RequestParam String uuid) throws LoginException, ProductException, CategoryException{
		
		Product savedProduct = productService.addProduct(product, uuid);
		
		return new ResponseEntity<Product>(savedProduct, HttpStatus.ACCEPTED);
	}
}
