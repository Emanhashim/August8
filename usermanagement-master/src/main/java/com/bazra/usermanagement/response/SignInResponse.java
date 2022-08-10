package com.bazra.usermanagement.response;

import java.math.BigDecimal;
import java.util.Set;

import org.json.simple.JSONObject;

import com.bazra.usermanagement.model.Role;
import com.bazra.usermanagement.model.Roles;
/**
 * SignIn Response
 * @author Bemnet
 * @version 4/2022
 */

public class SignInResponse {
    private String jwt;
    private String type = "Bearer ";

    private JSONObject user = new JSONObject();
   
   
    
    public SignInResponse(int i, String firsname, String username, String string, String country,BigDecimal balance, String gender, String jwt) {
    	JSONObject jo = new JSONObject();
    	this.jwt = jwt;

        jo.put("id", i);
        jo.put("firstname", firsname);
        jo.put("username", username);
        jo.put("country", country);
        jo.put("gender", gender);
        jo.put("balance", balance);
 
       this.user=jo;
  
    }


	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
        return jwt;
    }


	public String getType() {
		return type;
	}






	public void setType(String type) {
		this.type = type;
	}






	public JSONObject getUser() {
		return user;
	}






	public void setUser(JSONObject user) {
		this.user = user;
	}
	
	

    

}
