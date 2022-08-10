package com.bazra.usermanagement.response;

import java.math.BigDecimal;

public class BalanceResponse {
    private BigDecimal balance;
    private String message;
    private String username;
    private BigDecimal amount;
    
    
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal balance) {
        this.balance = balance;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public BalanceResponse( BigDecimal balance, String message, String username,BigDecimal amount) {
    	
        this.username=username;
        this.balance = balance;
        this.message= message;
        this.amount= amount;
       }
    public BalanceResponse(String message) {
    	
        this.message=message;
    }
}
