package com.bazra.usermanagement.request;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

public class WithdrawRequest {

    @ApiModelProperty(value= "This Variable Holds Amount To Be Withdraw")
    @NotBlank(message = "Enter withdraw amount")
    private BigDecimal amount;

    @ApiModelProperty(value= "This Variable Holds Account Number or Phone Number To Be Withdraw ")
    @NotBlank(message = "Enter your account")
    private String fromAccountNumber;
    
    
    
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
    
}
