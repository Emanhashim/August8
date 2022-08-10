package com.bazra.usermanagement.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class BalanceRequest {

    @ApiModelProperty(value= "This Is Users Account Number or Phone NUmber, To Check Current Balance ")
    @NotBlank
    private String accountNumber;
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
