package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entities.Cart;
import com.ecommerce.entities.CartProductDTO;
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

	@DeleteMapping("/cart/{productId}")
	public ResponseEntity<String> deleteFromCart(@PathVariable("productId") Integer productId,
			@RequestParam String uuid) throws CustomerException, ProductException, LoginException, CartException {

		String res = cartService.deleteFromCart(productId, uuid);

		return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);

	}

	@GetMapping("/cart")
	public ResponseEntity<Cart> getCart(@RequestParam String uuid)
			throws CustomerException, ProductException, LoginException, CartException {

		Cart cart = cartService.getCartDetails(uuid);

		return new ResponseEntity<Cart>(cart, HttpStatus.OK);

	}

	@DeleteMapping("/cart")
	public ResponseEntity<String> deleteAllCart(@RequestParam String uuid)
			throws CustomerException, ProductException, LoginException, CartException {

		String res = cartService.deleteAllFromCart(uuid);

		return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);

	}

	@PutMapping("/cart/{productId}/{quantity}")
	public ResponseEntity<CartProductDTO> updateQuantity(@PathVariable("productId") Integer productId,
			@PathVariable("quantity") Integer quantity, @RequestParam String uuid)
			throws CustomerException, ProductException, LoginException, CartException {

		CartProductDTO cart = cartService.updateProductQuantity(productId, quantity, uuid);

		return new ResponseEntity<CartProductDTO>(cart, HttpStatus.ACCEPTED);
	}
}
