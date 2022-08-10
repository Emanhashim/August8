package com.bazra.usermanagement.response;

import java.util.Optional;

import com.bazra.usermanagement.model.MerchantInfo;

public class MerchantResponse {

	private Integer id;

	private String firstName;

    private String lastName;

    private String companyName;
    
    private String username;
   
    private String licenceNumber;
   
    private String businessLNum;
    private String roles;
	private Optional<MerchantInfo> agentInfos;
	
	 
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
	public Optional<MerchantInfo> getAgentInfos() {
		return agentInfos;
	}
	public void setAgentInfos(Optional<MerchantInfo> agentInfos) {
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
		
	public MerchantResponse(Optional<MerchantInfo> agentInfos) {
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
