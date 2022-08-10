package com.bazra.usermanagement.response;

import java.math.BigDecimal;

public class CommissionResponse {

	private BigDecimal commission;
	private String message;
	private String username;
	private BigDecimal amount;

	public CommissionResponse(BigDecimal commission, String message, String username,BigDecimal amount) {

		this.username = username;
		this.commission = commission;
		this.message = message;
		this.amount = amount;
	}
	

	public BigDecimal getCommission() {
		return commission;
	}


	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}


	public CommissionResponse(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
