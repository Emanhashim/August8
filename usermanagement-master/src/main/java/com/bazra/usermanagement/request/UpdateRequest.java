package com.bazra.usermanagement.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UpdateRequest {
	@ApiModelProperty(value= "This is users FirstName ")
	@NotBlank

	private String firstName;

	@ApiModelProperty(value= "This is users LastName ")
	@NotBlank
	private String lastName;

	@ApiModelProperty(value= "This is users Old Password ")
	@NotBlank
	private String password;

	@ApiModelProperty(value= "This is users PhoneNumber ")
	@NotBlank
	private String username;
	@ApiModelProperty(value= "This is users New Password ")
	@NotBlank
	private String newPassword;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
