package com.bazra.usermanagement.response;

import java.math.BigDecimal;

public class TotalResponseAgents {


	private Integer totalAgents;
	private String message;

	
	
	public Integer getTotalAgents() {
		return totalAgents;
	}
	public void setTotalAgents(Integer totalAgents) {
		this.totalAgents = totalAgents;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TotalResponseAgents(Integer totalAgents, String message) {
		super();
		this.totalAgents = totalAgents;
		this.message = message;
	}
	public TotalResponseAgents(String message) {
		super();
		this.message = message;
	}
	
	
	
}
