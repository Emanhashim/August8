package com.bazra.usermanagement.request;

import java.math.BigDecimal;

import com.bazra.usermanagement.model.Levels;

public class CreateSettingRequest {
	private String settingName;
	private BigDecimal value;
	private Levels levels;
	
	public Levels getLevels() {
		return levels;
	}
	public void setLevels(Levels levels) {
		this.levels = levels;
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
