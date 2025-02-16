package com.tweetapp.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserTo extends BaseUserTo implements Serializable {

	private static final long serialVersionUID = 101L;

	@NotBlank(message = "Confirm Password is mandatory")
	@Size(min = 5, max = 18, message = "The confirm password '${validatedValue}' must be between {min} and {max} characters long")
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
