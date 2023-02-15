package com.ecommerce.services;

import com.ecommerce.entities.Cart;
import com.ecommerce.entities.CartProductDTO;
import com.ecommerce.exceptions.CartException;
import com.ecommerce.exceptions.CustomerException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.exceptions.ProductException;

public interface CartServices {

	public Cart addToCart(Integer productId,Integer quantity, String uuid) throws CustomerException,ProductException, LoginException, CartException;
	
	public String deleteFromCart(Integer productId, String uuid) throws CustomerException,ProductException, LoginException, CartException;
	
	public Cart getCartDetails(String uuid) throws CustomerException,ProductException, LoginException, CartException;
	
	public String deleteAllFromCart(String uuid) throws CustomerException,ProductException, LoginException, CartException;
	
	public CartProductDTO updateProductQuantity(Integer productId, Integer quantity, String uuid) throws CustomerException,ProductException, LoginException, CartException;
}
