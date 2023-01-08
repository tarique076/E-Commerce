package com.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @RequestParam String uuid) throws LoginException, ProductException{
		
		Product updatedProduct = productService.updateProduct(product, uuid);
		
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("productId") Integer productId, @RequestParam String uuid) throws ProductException, LoginException{
		
		Product deletedProduct = productService.deleteProductById(productId, uuid);
		
		return new ResponseEntity<Product>(deletedProduct, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam String uuid) throws ProductException, LoginException{
		
		List<Product> products = productService.getAllProducts(uuid);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Integer productId, @RequestParam String uuid) throws ProductException, LoginException{
		
		Product product = productService.getProductById(productId, uuid);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping("/products/{productName}")
	public ResponseEntity<List<Product>> getProductByName(@RequestParam String uuid, @PathVariable("productName") String productName) throws ProductException, LoginException{
		
		List<Product> products = productService.getProductByName(productName, uuid);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/products/category/")
	public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String categoryName, @RequestParam String uuid) throws CategoryException, ProductException, LoginException{
		
		List<Product> products = productService.getProductByCategory(categoryName, uuid);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("products/sortByRatings")
	public ResponseEntity<List<Product>> sortProductByRating(@RequestParam String uuid) throws ProductException, LoginException{
		
		List<Product> products = productService.sortProductByRating(uuid);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("products/filterByRatings/{minRating}/{maxRating}")
	public ResponseEntity<List<Product>> filterProductsByRating(@PathVariable("minRating") Double minRating, @PathVariable("maxRating") Double maxRating, @RequestParam String uuid) throws ProductException, LoginException{
		
		List<Product> products = productService.filterProductByRating(uuid, minRating, maxRating);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("products/sortByPriceAsc")
	public ResponseEntity<List<Product>> sortProductByPriceAsc(@RequestParam String uuid) throws ProductException, LoginException{
		
		List<Product> products = productService.sortProductByPriceAsc(uuid);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("products/sortByPriceDesc")
	public ResponseEntity<List<Product>> sortProductByPriceDesc(@RequestParam String uuid) throws ProductException, LoginException{
		
		List<Product> products = productService.sortProductByPriceDesc(uuid);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("products/filterByPrice/{minPrice}/{maxPrice}")
	public ResponseEntity<List<Product>> filterProductsByPrice(@PathVariable("minPrice") Double minPrice, @PathVariable("maxPrice") Double maxPrice, @RequestParam String uuid) throws ProductException, LoginException{
		
		List<Product> products = productService.filterProductByPrice(uuid, minPrice, maxPrice);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
