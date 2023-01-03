package com.ecommerce.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	
	@NotNull(message = "House no. not provided.")
	private String houseNo;
	
	@NotNull(message = "Address field cannot be null")
	private String address;
	
	@NotNull(message = "City not provided.")
	private String city;
	
	@NotNull(message = "State not provided.")
	private String state;
	
	@Pattern(regexp = "[1-9]{1}[0-9]{5}",message = "Pincode must of 6 digits")
	@NotNull
	private String pincode;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	private Customer customer;

	public Address(@NotNull(message = "House no. not provided.") String houseNo,
			@NotNull(message = "Address field cannot be null") String address,
			@NotNull(message = "City not provided.") String city,
			@NotNull(message = "State not provided.") String state,
			@Pattern(regexp = "[1-9]{1}[0-9]{5}", message = "Pincode must of 6 digits") @NotNull String pincode,
			Customer customer) {
		super();
		this.houseNo = houseNo;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.customer = customer;
	}
	
	
}
