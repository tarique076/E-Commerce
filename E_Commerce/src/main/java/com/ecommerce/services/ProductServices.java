package com.ecommerce.services;

import java.util.List;

import com.ecommerce.entities.Product;
import com.ecommerce.exceptions.CategoryException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.exceptions.ProductException;

public interface ProductServices {

	public List<Product> getAllProducts(String uuid) throws ProductException, LoginException;
	
	public Product getProductById(Integer productId, String uuid) throws ProductException, LoginException;
	
	public List<Product> getProductByName(String name, String uuid) throws ProductException, LoginException;
	
	public List<Product> getProductByCategory(String category, String uuid ) throws CategoryException, ProductException, LoginException;
	
	public Product addProduct(Product product, String uuid) throws LoginException, ProductException, CategoryException;
	
	public Product updateProduct(Product product, String uuid) throws LoginException, ProductException;
	
	public Product deleteProductById(Integer productId, String uuid) throws ProductException, LoginException;
	
	public List<Product> sortProductByRating(String uuid) throws ProductException, LoginException;
	
	public List<Product> filterProductByRating(String uuid, Double minRating, Double maxRating) throws ProductException, LoginException;
	
	public List<Product> sortProductByPriceAsc(String uuid) throws ProductException, LoginException;
	
	public List<Product> sortProductByPriceDesc(String uuid) throws ProductException, LoginException;
	
	public List<Product> filterProductByPrice(String uuid, Double minPrice, Double maxPrice) throws ProductException, LoginException;
}
