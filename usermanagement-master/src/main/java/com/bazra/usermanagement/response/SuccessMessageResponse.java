package com.bazra.usermanagement.response;

public class SuccessMessageResponse {
    private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SuccessMessageResponse(String message) {
		super();
		this.message = message;
	}
    
    
}
