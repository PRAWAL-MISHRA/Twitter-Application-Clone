package com.tweetapp.tweetappcli.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ChangePasswordTo extends BaseUserTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "New Password is mandatory")
	@Size(min = 5, max = 18, message = "The new password '${validatedValue}' must be between {min} and {max} characters long")
	private String newPassword;

	@NotBlank(message = "Confirm New Password is mandatory")
	@Size(min = 5, max = 18, message = "The confirm new password '${validatedValue}' must be between {min} and {max} characters long")
	private String confirmNewPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

}
