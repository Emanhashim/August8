package com.bazra.usermanagement.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class TransactionRequest {

	@ApiModelProperty(value= "This Variable Holds The Account Number or Phone Number  ")
	@NotBlank(message = "Enter Account Number")
    private String accountNumber;

	public String getaccountNumber() {
		return accountNumber;
	}

	public void setaccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

    
}
