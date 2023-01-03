package com.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private Integer productId;
	
	private String productName;
	
	private String brand;
	
	private Integer quantity;
	
	private Double price;
}
