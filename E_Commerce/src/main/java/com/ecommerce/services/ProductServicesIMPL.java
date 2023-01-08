package com.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.Category;
import com.ecommerce.entities.CurrentAdminSession;
import com.ecommerce.entities.Product;
import com.ecommerce.exceptions.CategoryException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.repository.CategoryDAO;
import com.ecommerce.repository.CurrentAdminSessioDAO;
import com.ecommerce.repository.ProductDAO;

@Service
public class ProductServicesIMPL implements ProductServices{

	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private CurrentAdminSessioDAO adminSessionDao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Override
	public List<Product> getAllProducts(String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductIById(Integer productId, String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductByName(String name, String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductByCategoryId(Integer categoryId, String uuid)
			throws CategoryException, ProductException, LoginException {
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

	@Override
	public Product deleteProductById(Integer productId, String uuid) throws ProductException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

}
