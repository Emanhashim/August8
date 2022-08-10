package com.bazra.usermanagement.response;

import java.math.BigDecimal;

public class RevenueResponse {

	private BigDecimal revenue;
	private String message;

	
	public BigDecimal getRevenue() {
		return revenue;
	}
	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}
	public String getMessage() {
	    return message;
	}
	public void setMessage(String message) {
	    this.message = message;
	}
	public RevenueResponse(BigDecimal revenue, String message) {
		super();
		this.revenue = revenue;
		this.message = message;
	}
	public RevenueResponse(String message) {
		super();
		this.message = message;
	}
	
	
}