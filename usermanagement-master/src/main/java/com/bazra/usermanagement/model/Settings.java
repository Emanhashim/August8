package com.bazra.usermanagement.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "Setting")
public class Settings {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "setting_id")
    private int id;

	
	private String settingName;
	private Levels levels;
	@Column(precision = 15, scale = 8)
	private BigDecimal value;
	
	
	
	
//	public Settings() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	public Settings(String settingName, BigDecimal value, Levels levels) {
		super();
		this.settingName = settingName;
		this.value = value;
		this.levels = levels;
	}
	
	public int getId() {
		return id;
	}
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
