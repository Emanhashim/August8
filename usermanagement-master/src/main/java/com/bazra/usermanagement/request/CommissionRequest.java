package com.bazra.usermanagement.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class CommissionRequest {


	@ApiModelProperty(value= "This Is Users Account Number or Phone NUmber, To Check Commission for an Agent ")
	@NotBlank
    private String accountNumber;
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
