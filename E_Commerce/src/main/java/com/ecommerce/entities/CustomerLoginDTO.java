package com.ecommerce.entities;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoginDTO {

	@NotNull(message = "Mobile no. cannot be null")
	private String mobile;
	
	@NotNull(message = "Password cannot be null")
	private String password;
}
