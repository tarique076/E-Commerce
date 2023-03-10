package com.ecommerce.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDTO {

	@Id
	private Integer productId;

	private String productName;

	private Double price;

	private String color;

	private String brand;

	private Integer quantity;
	
	private String stockStatus;
}
