package com.bazra.usermanagement.response;

public class AdminSignupResponse {
	private String username;
	private String message;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AdminSignupResponse(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}
}
