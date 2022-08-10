package com.bazra.usermanagement.response;

import java.math.BigDecimal;

public class TotalResponseCustomers {

	private Integer totalCustomers;
	private String message;

	
	
	public Integer getTotalCustomers() {
		return totalCustomers;
	}
	public void setTotalCustomers(Integer totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TotalResponseCustomers(Integer totalCustomers, String message) {
		super();
		this.totalCustomers = totalCustomers;
		this.message = message;
	}
	public TotalResponseCustomers(String message) {
		super();
		this.message = message;
	}
	
	
	
}
