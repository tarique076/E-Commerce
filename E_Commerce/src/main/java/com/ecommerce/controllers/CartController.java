package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.Cart;
import com.ecommerce.exceptions.CartException;
import com.ecommerce.exceptions.CustomerException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.services.CartServices;

@RestController
public class CartController {

	@Autowired
	private CartServices cartService;

	@PostMapping("/cart/{productId}/{quantity}")
	public ResponseEntity<Cart> addToCart(@PathVariable("productId") Integer productId,
			@PathVariable("quantity") Integer quantity, @RequestParam String uuid)
			throws CustomerException, ProductException, LoginException, CartException {

		Cart cart = cartService.addToCart(productId, quantity, uuid);

		return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
	}
}
