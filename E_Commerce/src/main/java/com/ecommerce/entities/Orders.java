package com.ecommerce.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	private Integer customerId;

	private LocalDateTime orderDate;

	@NotNull(message = "Order status should not be null")
	private String orderStatus;

	@NotNull(message = "Payment status should not be null")
	private String paymentStatus;

	@NotNull(message = "Transaction mode should not be null")
	private String transactionMode;

	private Integer quantity;

	private Double totalCost;

	@Embedded
	@ElementCollection
	private List<OrderDTO> products = new ArrayList<>();

	public Orders(LocalDateTime orderDate, @NotNull(message = "Order status should not be null") String orderStatus,
			@NotNull(message = "Payment status should not be null") String paymentStatus,
			@NotNull(message = "Transaction mode should not be null") String transactionMode, Integer quantity,
			Double totalCost) {
		super();
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}
	
	
}
