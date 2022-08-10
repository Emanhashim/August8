package com.bazra.usermanagement.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bazra.usermanagement.model.AgentInfo;
import com.bazra.usermanagement.model.UserInfo;

import io.swagger.annotations.ApiModelProperty;

public class AgentResponse {
	private Integer id;

	private String firstName;

    private String lastName;

    private String companyName;
    
    private String username;
   
    private String licenceNumber;
   
    private String businessLNum;
    private String roles;
	 private Optional<AgentInfo> agentInfos;
	
	 
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLicenceNumber() {
		return licenceNumber;
	}
	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}
	public String getBusinessLNum() {
		return businessLNum;
	}
	public void setBusinessLNum(String businessLNum) {
		this.businessLNum = businessLNum;
	}
	public String getRole() {
		return roles;
	}
	public void setRole(String role) {
		this.roles = role;
	}
	public Optional<AgentInfo> getAgentInfos() {
		return agentInfos;
	}
	public void setAgentInfos(Optional<AgentInfo> agentInfos) {
		this.agentInfos = agentInfos;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
		
	public AgentResponse(Optional<AgentInfo> agentInfos) {
		super();
		this.id = agentInfos.get().getId();
		this.username = agentInfos.get().getUsername();
		this.roles = agentInfos.get().getRoles();
		this.firstName = agentInfos.get().getFirstName();
		this.lastName = agentInfos.get().getLastName();
		this.businessLNum =agentInfos.get().getBusinessLNum();
		this.companyName=agentInfos.get().getCompanyName();
		this.licenceNumber=agentInfos.get().getLicenceNumber();
		
	}

}
