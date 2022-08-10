package com.bazra.usermanagement.request;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

public class DepositRequest {

    @ApiModelProperty(value= "This Variable Holds Amount Number To Be Deposited  ")
    @NotBlank(message = "Enter Deposit amount")
    private BigDecimal amount;

    @ApiModelProperty(value= "This Is Users Account Number or Phone NUmber to be Sent to ")
    @NotBlank(message = "Enter your account")
    private String toAccountNumber;
    
   

    public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
