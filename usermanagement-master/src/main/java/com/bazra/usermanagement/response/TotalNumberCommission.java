package com.bazra.usermanagement.response;

import java.math.BigDecimal;

public class TotalNumberCommission {


	private BigDecimal totalCommission;

	private String message;

	
	
	public BigDecimal getTotalCommission() {
		return totalCommission;
	}
	public void setTotalCommission(BigDecimal totalCommission) {
		this.totalCommission = totalCommission;
	}

	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TotalNumberCommission(BigDecimal totalCommission, String message) {
		super();
		this.totalCommission = totalCommission;
		this.message = message;
	}
	public TotalNumberCommission(String message) {
		super();
		this.message = message;
	}
	
	
	
}

