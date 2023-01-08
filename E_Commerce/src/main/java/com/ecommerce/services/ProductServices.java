package com.ecommerce.services;

import java.util.List;

import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductDTO;
import com.ecommerce.exceptions.CategoryException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.exceptions.ProductException;

public interface ProductServices {

	public List<Product> getAllProducts(String uuid) throws ProductException, LoginException;
	
	public Product getProductIById(Integer productId, String uuid) throws ProductException, LoginException;
	
	public List<Product> getProductByName(String name, String uuid) throws ProductException, LoginException;
	
	public List<Product> getProductByCategoryId(Integer categoryId, String uuid ) throws CategoryException, ProductException, LoginException;
	
	public Product addProduct(Product product, String uuid) throws LoginException, ProductException, CategoryException;
	
	public Product updateProduct(Product product, String uuid) throws LoginException, ProductException;
	
	public Product deleteProductById(Integer productId, String uuid) throws ProductException, LoginException;
}
