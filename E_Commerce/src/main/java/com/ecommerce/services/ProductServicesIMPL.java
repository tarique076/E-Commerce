package com.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.Category;
import com.ecommerce.entities.CurrentAdminSession;
import com.ecommerce.entities.CurrentCustomerSession;
import com.ecommerce.entities.Product;
import com.ecommerce.exceptions.CategoryException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.repository.CategoryDAO;
import com.ecommerce.repository.CurrentAdminSessioDAO;
import com.ecommerce.repository.CurrentCustomerSessionDAO;
import com.ecommerce.repository.ProductDAO;

@Service
public class ProductServicesIMPL implements ProductServices{

	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private CurrentAdminSessioDAO adminSessionDao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Autowired
	private CurrentCustomerSessionDAO customerSessionDao;
	
	@Override
	public List<Product> getAllProducts(String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		CurrentAdminSession adminSession = adminSessionDao.findByUuid(uuid);
		CurrentCustomerSession customerSession = customerSessionDao.findByUuid(uuid);
		
		if(adminSession==null && customerSession==null)
			throw new LoginException("No active session found with uuid " + uuid);
		
		return productDao.findAll();
	}

	@Override
	public Product getProductById(Integer productId, String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		CurrentCustomerSession customerSession = customerSessionDao.findByUuid(uuid);
		CurrentAdminSession adminSession = adminSessionDao.findByUuid(uuid);
		
		if(customerSession==null && adminSession==null)
			throw new LoginException("No active session with uuid "+uuid);
		
		Product product = productDao.findById(productId).orElseThrow(() -> new ProductException("No product found with productId "+productId));
		
		return product;
	}

	@Override
	public List<Product> getProductByName(String name, String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		CurrentCustomerSession customerSession = customerSessionDao.findByUuid(uuid);
		CurrentAdminSession adminSession = adminSessionDao.findByUuid(uuid);
		
		if(customerSession==null && adminSession==null)
			throw new LoginException("No active session with uuid "+uuid);
		
		List<Product> products = productDao.findByProductNameContainingIgnoreCase(name);
		
		if(products.isEmpty())
			throw new ProductException("No products available matching " + name);
		
		return products;
	}

	@Override
	public List<Product> getProductByCategory(String category, String uuid)
			throws CategoryException, ProductException, LoginException {
		// TODO Auto-generated method stub
		CurrentCustomerSession customerSession = customerSessionDao.findByUuid(uuid);
		CurrentAdminSession adminSession = adminSessionDao.findByUuid(uuid);
		
		if(customerSession==null && adminSession==null)
			throw new LoginException("No active session with uuid "+uuid);
		
		List<Product> products = productDao.findByCategory(category);
		
		if(products.isEmpty())
			throw new ProductException("No products available under category " + category);
		
		return products;
	}

	@Override
	public Product addProduct(Product product, String uuid) throws LoginException, ProductException, CategoryException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession session = adminSessionDao.findByUuid(uuid);
		if(session==null)
			throw new LoginException("Invalid uuid. Please login first to add products.");
		
		Product savedProduct = productDao.save(product);
		
		String categoryName = product.getCategory();
		
		Category category = categoryDao.findByCategoryName(categoryName);
		
		if(category!=null) {
			category.getProducts().add(savedProduct);
			categoryDao.save(category);
		}else {
			Category newCategory = new Category();
			newCategory.setCategoryName(categoryName);
			newCategory.getProducts().add(savedProduct);
			categoryDao.save(newCategory);
		}
		
		return savedProduct;
	}

	@Override
	public Product updateProduct(Product product, String uuid) throws LoginException, ProductException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession session = adminSessionDao.findByUuid(uuid);
		if(session==null)
			throw new LoginException("No admin session found with uuid " + uuid);
		
		Product existingProduct = productDao.findById(product.getProductId()).orElseThrow(()-> new ProductException("Invalid productId."));
		
		if(!existingProduct.getCategory().equals(product.getCategory())) {
			
			Category existingCategory = categoryDao.findByCategoryName(existingProduct.getCategory());
			existingCategory.getProducts().remove(existingProduct);
			
			Category checkCategory = categoryDao.findByCategoryName(product.getCategory());
			
			if(checkCategory==null) {
				Category newCategory = new Category();
				newCategory.setCategoryName(product.getCategory());
				newCategory.getProducts().add(product);
				categoryDao.save(newCategory);
			}else {
				checkCategory.getProducts().add(product);
				categoryDao.save(checkCategory);
			}
		}
		return productDao.save(product);
	}

	@Override
	public Product deleteProductById(Integer productId, String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession session = adminSessionDao.findByUuid(uuid);
		if(session==null)
			throw new LoginException("No admin session found with uuid " + uuid);
		
		Product product = productDao.findById(productId).orElseThrow(()-> new ProductException("Invalid productId."));
		
		Category category = categoryDao.findByCategoryName(product.getCategory());
		
		if(category!=null) {
			category.getProducts().remove(product);
			categoryDao.save(category);
		}
		
		productDao.delete(product);
		
		return product;
	}

	@Override
	public List<Product> sortProductByRating(String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		CurrentCustomerSession customerSession = customerSessionDao.findByUuid(uuid);
		CurrentAdminSession adminSession = adminSessionDao.findByUuid(uuid);
		
		if(customerSession==null && adminSession==null)
			throw new LoginException("No active session with uuid "+uuid);
		
		List<Product> products = productDao.findByOrderByRatingDesc();
		
		if(products.isEmpty())
			throw new ProductException("No products available");
		
		return products;
	}

	@Override
	public List<Product> filterProductByRating(String uuid, Double min, Double max)
			throws ProductException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentCustomerSession customerSession = customerSessionDao.findByUuid(uuid);
		CurrentAdminSession adminSession = adminSessionDao.findByUuid(uuid);
		
		if(customerSession==null && adminSession==null)
			throw new LoginException("No active session with uuid "+uuid);
		
		List<Product> products = productDao.findByRatingBetween(min, max);
		
		if(products.isEmpty())
			throw new ProductException("No products found in this ratings range");
		
		return products;
	}

	@Override
	public List<Product> sortProductByPriceAsc(String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		CurrentCustomerSession customerSession = customerSessionDao.findByUuid(uuid);
		CurrentAdminSession adminSession = adminSessionDao.findByUuid(uuid);
		
		if(customerSession==null && adminSession==null)
			throw new LoginException("No active session with uuid "+uuid);
		
		List<Product> products = productDao.findByOrderByPriceAsc();
		
		if(products.isEmpty())
			throw new ProductException("No products available.");
		
		return products;
	}

	@Override
	public List<Product> sortProductByPriceDesc(String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		CurrentCustomerSession customerSession = customerSessionDao.findByUuid(uuid);
		CurrentAdminSession adminSession = adminSessionDao.findByUuid(uuid);
		
		if(customerSession==null && adminSession==null)
			throw new LoginException("No active session with uuid "+uuid);
		
		List<Product> products = productDao.findByOrderByPriceDesc();
		
		if(products.isEmpty())
			throw new ProductException("No products available.");
		
		return products;
	}

	@Override
	public List<Product> filterProductByPrice(String uuid, Double min, Double max)
			throws ProductException, LoginException {
		// TODO Auto-generated method stub
		CurrentCustomerSession customerSession = customerSessionDao.findByUuid(uuid);
		CurrentAdminSession adminSession = adminSessionDao.findByUuid(uuid);
		
		if(customerSession==null && adminSession==null)
			throw new LoginException("No active session with uuid "+uuid);
		
		List<Product> products = productDao.findByPriceBetween(min, max);
		
		if(products.isEmpty())
			throw new ProductException("No products found in this price range");
		
		return products;
	}

}
