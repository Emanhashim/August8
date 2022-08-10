package com.bazra.usermanagement.response;

import java.math.BigDecimal;

public class SettingResponse {
	private String message;
	private String settingName;
	private BigDecimal value;
	
	public SettingResponse(String settingName, BigDecimal value, String message) {
		super();
		this.settingName = settingName;
		this.value = value;
		this.message = message;
	}
	
	

	public SettingResponse(String message) {
		super();
		this.message = message;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	

}
