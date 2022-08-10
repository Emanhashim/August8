package com.bazra.usermanagement.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AdminTransferRequest {
	@ApiModelProperty(value= "This Variable Holds Account Number to Be Transfered too ")
    @NotBlank(message = "Enter Transfer Account")
    private String toAccountNumber;
	
	@ApiModelProperty(value= "This Variable Holds Account Number to Be Transfered too ")
    @NotBlank(message = "Enter Transfer Account")
    private String fromAccountNumber;
	
    @ApiModelProperty(value= "This Variable Holds Amount to Be Transferd ")
    @NotBlank(message = "Minimum amount for transfer is 5")
    private BigDecimal amount;

    @ApiModelProperty(value= "This Variable Holds Remark or Message ")
    @NotBlank(message = "Enter your Remark")
    private String message;

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
    
}
