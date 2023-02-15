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
		if (session == null)
			throw new LoginException("No active session found with " + uuid);

		Product product = productDao.findById(productId).orElseThrow(() -> new ProductException("Invalid productId."));

		Customer customer = customerDao.findById(session.getUserId())
				.orElseThrow(() -> new CustomerException("Customer not found."));

		CartProductDTO dto = new CartProductDTO();
		dto.setProductId(product.getProductId());
		dto.setProductName(product.getProductName());
		dto.setBrand(product.getBrand());
		dto.setColor(product.getColor());
		dto.setQuantity(quantity);
		dto.setPrice(product.getPrice());
		if (product.getQuantity() > 0)
			dto.setStockStatus("In stock.");
		else
			dto.setStockStatus("Out of stock.");

		if (customer.getCart() == null) {
			Cart newCart = new Cart();
			newCart.setQuantity(quantity);
			newCart.setTotalCost(dto.getPrice() * dto.getQuantity());
			newCart.getProducts().add(dto);
			customer.setCart(newCart);
		} else {
			customer.getCart().setQuantity(customer.getCart().getQuantity() + quantity);
			customer.getCart().setTotalCost(customer.getCart().getTotalCost() + (dto.getPrice() * dto.getQuantity()));
			customer.getCart().getProducts().add(dto);
		}

		customerDao.save(customer);

		return cartDao.save(customer.getCart());
	}

	@Override
	public String deleteFromCart(Integer productId, String uuid)
			throws CustomerException, ProductException, LoginException, CartException {
		// TODO Auto-generated method stub
		CurrentCustomerSession session = customerSessionDao.findByUuid(uuid);
		if (session == null)
			throw new LoginException("No active session found with " + uuid);

		Customer customer = customerDao.findById(session.getUserId())
				.orElseThrow(() -> new CustomerException("Invalid customer details."));

		if (customer.getCart() == null)
			throw new CartException("Cart is already empty. Nothing to delete.");

		CartProductDTO dto = cartProductDao.findById(productId)
				.orElseThrow(() -> new ProductException("No product found in cart with productId " + productId));

		customer.getCart().getProducts().remove(dto);

		if (customer.getCart().getProducts().isEmpty()) {
			Cart cart = customer.getCart();
			cartDao.delete(cart);
			customer.setCart(null);
		}

		customerDao.save(customer);

		return "Product deleted from cart.";
	}

	@Override
	public Cart getCartDetails(String uuid) throws CustomerException, ProductException, LoginException, CartException {
		// TODO Auto-generated method stub
		CurrentCustomerSession session = customerSessionDao.findByUuid(uuid);
		if (session == null)
			throw new LoginException("No active session found with " + uuid);

		Customer customer = customerDao.findById(session.getUserId())
				.orElseThrow(() -> new CustomerException("No customer found with uuid " + uuid));

		if (customer.getCart() == null)
			throw new CartException("Cart is empty.");

		return customer.getCart();
	}

	@Override
	public String deleteAllFromCart(String uuid)
			throws CustomerException, ProductException, LoginException, CartException {
		// TODO Auto-generated method stub
		CurrentCustomerSession session = customerSessionDao.findByUuid(uuid);
		if (session == null)
			throw new LoginException("No active session found with " + uuid);

		Customer customer = customerDao.findById(session.getUserId())
				.orElseThrow(() -> new CustomerException("No customer found with uuid " + uuid));

		if (customer.getCart() == null)
			throw new CartException("Cart is empty.");
		
		cartDao.delete(customer.getCart());
		
		return "Cart deleted successfully !";
	}

	@Override
	public CartProductDTO updateProductQuantity(Integer productId, Integer quantity, String uuid)
			throws CustomerException, ProductException, LoginException, CartException {
		// TODO Auto-generated method stub
		CurrentCustomerSession session = customerSessionDao.findByUuid(uuid);
		if (session == null)
			throw new LoginException("No active session found with " + uuid);

		Customer customer = customerDao.findById(session.getUserId())
				.orElseThrow(() -> new CustomerException("Invalid customer details."));

		if (customer.getCart() == null)
			throw new CartException("No items added in cart.");

		CartProductDTO dto = cartProductDao.findById(productId)
				.orElseThrow(() -> new ProductException("No product found in cart with productId " + productId));
		
		dto.setQuantity(quantity);
		
		customerDao.save(customer);
		
		return cartProductDao.save(dto);
	}

}
