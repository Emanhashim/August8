package com.bazra.usermanagement.response;

import java.math.BigDecimal;

import com.bazra.usermanagement.model.Account;

public class AccountResponse {
	
	private String accountNumber;
	private BigDecimal balance;
	private long user_id;
	private String username;
    private String type;
    private boolean blocked;
	
    
    
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public AccountResponse( String accountNumber, BigDecimal balance, long user_id, String username,
			String type) {
		super();
		
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.user_id = user_id;
		this.username = username;
		this.type = type;
	}
	public AccountResponse(Account account) {
		this.accountNumber=account.getAccountNumber();
		this.balance=account.getBalance();
		this.user_id=account.getUser_id();
		this.username=account.getUsername();
		this.type=account.getType();
		this.blocked=account.isBlocked();
	}
	
	
	
    
}
