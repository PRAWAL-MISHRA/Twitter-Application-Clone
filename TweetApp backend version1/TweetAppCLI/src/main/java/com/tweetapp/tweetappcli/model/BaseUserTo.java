package com.tweetapp.tweetappcli.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class BaseUserTo implements Serializable {

	private static final long serialVersionUID = 101L;

	private Integer id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 5, max = 18, message = "The password '${validatedValue}' must be between {min} and {max} characters long")
	private String password;

	private String gender;
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "BaseUserTo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", gender='" + gender + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
