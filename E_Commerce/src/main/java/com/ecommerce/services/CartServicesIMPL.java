package com.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entities.Cart;
import com.ecommerce.entities.CartProductDTO;
import com.ecommerce.entities.CurrentCustomerSession;
import com.ecommerce.entities.Customer;
import com.ecommerce.entities.Product;
import com.ecommerce.exceptions.CartException;
import com.ecommerce.exceptions.CustomerException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.repository.CartDAO;
import com.ecommerce.repository.CartProductDtoDAO;
import com.ecommerce.repository.CurrentCustomerSessionDAO;
import com.ecommerce.repository.CustomerDAO;
import com.ecommerce.repository.ProductDAO;

@Service
public class CartServicesIMPL implements CartServices {
	
	@Autowired
	private CurrentCustomerSessionDAO customerSessionDao;
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private CartProductDtoDAO cartProductDao;
	
	@Autowired
	private CartDAO cartDao;

	@Override
	public Cart addToCart(Integer productId, Integer quantity, String uuid)
			throws CustomerException, ProductException, LoginException, CartException {
		// TODO Auto-generated method stub
		CurrentCustomerSession session = customerSessionDao.findByUuid(uuid);
		if(session==null)
			throw new LoginException("No active session found with "+uuid);
		
		Product product = productDao.findById(productId).orElseThrow(() -> new ProductException("Invalid productId."));
		
		Customer customer = customerDao.findById(session.getUserId()).orElseThrow(() -> new CustomerException("Customer not found."));
		
		CartProductDTO dto = new CartProductDTO();
		dto.setProductId(product.getProductId());
		dto.setProductName(product.getProductName());
		dto.setBrand(product.getBrand());
		dto.setColor(product.getColor());
		dto.setQuantity(quantity);
		dto.setPrice(product.getMrp());
		if(product.getQuantity()>0)
			dto.setStockStatus("In stock.");
		else
			dto.setStockStatus("Out of stock.");
		
		customer.getCart().getProducts().add(dto);
		if(customer.getCart().getQuantity()==null) {
			customer.getCart().setQuantity(quantity);
		}else
			customer.getCart().setQuantity(customer.getCart().getQuantity()+quantity);
		
		if(customer.getCart().getTotalCost()==null) {
			customer.getCart().setTotalCost(dto.getPrice()*dto.getQuantity());
		}else
			customer.getCart().setTotalCost(customer.getCart().getTotalCost() + (dto.getPrice()*dto.getQuantity()));
		
		customerDao.save(customer);
		
		return cartDao.save(customer.getCart());
	}

	@Override
	public String deleteFromCart(Integer productId, String uuid)
			throws CustomerException, ProductException, LoginException, CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getCartDetails(String uuid) throws CustomerException, ProductException, LoginException, CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAllFromCart(String uuid)
			throws CustomerException, ProductException, LoginException, CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateProductQuantity(Integer productId, Integer quantity, String uuid)
			throws CustomerException, ProductException, LoginException, CartException {
		// TODO Auto-generated method stub
		return null;
	}

}
