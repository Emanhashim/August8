package com.bazra.usermanagement.response;

import java.math.BigDecimal;

import org.json.simple.JSONObject;

public class AgentSignInResponse {
	
	private String jwt;
    private String type = "Bearer ";

    private JSONObject agent = new JSONObject();
	
	public AgentSignInResponse(int id, String username, String roles, BigDecimal balance, String jwt) {
		JSONObject jo = new JSONObject();
    	this.jwt = jwt;

        jo.put("id", id);
        jo.put("username", username);
        jo.put("roles", roles);
        jo.put("balance", balance);
        
        this.agent=jo;
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

	public JSONObject getUser() {
		return agent;
	}

	public void setUser(JSONObject agent) {
		this.agent = agent;
	}
	
	
}
