package com.bazra.usermanagement.response;

public class MasterSignupResponse {
	
	private String message;
    private String username;
    private String roles;
    private String email;
    private String organizationType;
    
    
    
	public MasterSignupResponse(String message, String username, String roles, String email,
			String organizationType) {
		super();
		this.message = message;
		this.username = username;
		this.roles = roles;
		this.email = email;
		this.organizationType = organizationType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}
    
    
}
