package com.bazra.usermanagement.response;

import java.math.BigDecimal;

public class TotalResponseMerchants {




	private Integer totalMerchants;
	private String message;

	
	
	
	public Integer getTotalMerchants() {
		return totalMerchants;
	}
	public void setTotalMerchants(Integer totalMerchants) {
		this.totalMerchants = totalMerchants;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TotalResponseMerchants(Integer totalMerchants, String message) {
		super();
		this.totalMerchants = totalMerchants;
		this.message = message;
	}
	public TotalResponseMerchants(String message) {
		super();
		this.message = message;
	}
	

	
}
