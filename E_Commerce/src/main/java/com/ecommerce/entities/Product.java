package com.ecommerce.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@NotNull(message = "Product name should not be null")
	private String productName;
	
	@Min(value = 0, message = "MRP can not be negative value")
	private Double mrp;
	
	@NotNull(message = "Price can not be null")
	@Min(value = 0, message = "Product sale price can not be negative value")
	private Double price;
	
	@Max(value = 5, message = "Rating should be less than five")
	@Min(value = 1, message = "Rating should be greater than one")
	private Double rating;
	
	private Integer ratingCount;
	
	private String color;
	
	@NotNull(message = "Brand name can not be null")
	private String brand;
	
	@Min(value = 1, message = "Quantity should not be less than 1")
	@NotNull(message = "Quantity can not be null")
	private Integer quantity;
	
	private String description;
	
	private String category;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Feedback> feedback = new ArrayList<>();
}
