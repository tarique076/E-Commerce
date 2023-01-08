package com.ecommerce.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	@NotNull(message = "Product name should not be null")
	private String productName;
	
	@Min(value = 0, message = "MRP can not be negative value")
	private Double mrp;
	
	@NotNull(message = "Price can not be null")
	@Min(value = 0, message = "Product sale price can not be negative value")
	private Double price;
	
	private String color;
	
	@NotNull(message = "Brand name can not be null")
	private String brand;
	
	@Min(value = 1, message = "Quantity should not be less than 1")
	@NotNull(message = "Quantity can not be null")
	private Integer quantity;
	
	private String description;
	
	private String category;
}
