package com.ecommerce.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;

	@NotNull(message = "First name can not be null")
	private String firstName;

	private String lastName;

	@Email(message = "Invalid email address")
	private String email;
	
	@Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid mobile no.")
	@NotNull(message = "Mobile no. cannot be null.")
	@Column(unique = true)
	private String mobile;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,12}$", message = "Password should be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit")
	@NotNull
	private String password;
	
	private LocalDateTime registeredDate;

	public Admin(@NotNull(message = "First name can not be null") String firstName, String lastName,
			@Email(message = "Invalid email address") String email,
			@Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid mobile no.") @NotNull String mobile,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,12}$", message = "Password should be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit") @NotNull String password,
			LocalDateTime registeredDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.registeredDate = registeredDate;
	}
	
	
}
