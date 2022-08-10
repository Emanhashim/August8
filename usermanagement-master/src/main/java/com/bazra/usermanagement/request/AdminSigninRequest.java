package com.bazra.usermanagement.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class AdminSigninRequest {
	@ApiModelProperty(value= "This is Admin Phone Number ")
    @NotBlank
    @Size(min = 10)
    private String username;

    @ApiModelProperty(value= "This is users Password ")
    @NotBlank
    @Size(min = 6, message = "Password should be atlease 6 characters")
    private String password;

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
