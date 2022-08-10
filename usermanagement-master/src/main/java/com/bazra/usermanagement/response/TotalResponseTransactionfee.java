package com.bazra.usermanagement.response;

import java.math.BigDecimal;

public class TotalResponseTransactionfee {


	private BigDecimal totalTransactionfee;
	private String message;

	
	

	public BigDecimal getTotalTransactionfee() {
		return totalTransactionfee;
	}
	public void setTotalTransaction(BigDecimal totalTransactionfee) {
		this.totalTransactionfee = totalTransactionfee;
	}
	

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public TotalResponseTransactionfee(BigDecimal totalTransactionfee, String message) {
		super();
		this.totalTransactionfee = totalTransactionfee;
		this.message = message;
	}
	public TotalResponseTransactionfee(String message) {
		super();
		this.message = message;
	}
	
}
