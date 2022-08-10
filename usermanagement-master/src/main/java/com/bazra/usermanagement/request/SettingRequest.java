package com.bazra.usermanagement.request;

import java.math.BigDecimal;

import com.bazra.usermanagement.model.Levels;

public class SettingRequest {
	
	
	private String settingName;
	private BigDecimal value;
	private Levels level;
	
	
	public Levels getLevel() {
		return level;
	}
	public void setLevel(Levels level) {
		this.level = level;
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
