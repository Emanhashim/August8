package com.bazra.usermanagement.response;

import java.math.BigDecimal;

import org.json.simple.JSONObject;

public class AdminSigninResponse {
	private String jwt;
    private String type = "Bearer ";

    private JSONObject admin = new JSONObject();
    
    public AdminSigninResponse(int id, String username, String roles, BigDecimal balance, String jwt) {
		JSONObject jo = new JSONObject();
    	this.jwt = jwt;

        jo.put("id", id);
        jo.put("username", username);
        jo.put("roles", roles);
        jo.put("balance", balance);
        
        this.admin=jo;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JSONObject getAdmin() {
		return admin;
	}

	public void setAdmin(JSONObject admin) {
		this.admin = admin;
	}
    
    
}
