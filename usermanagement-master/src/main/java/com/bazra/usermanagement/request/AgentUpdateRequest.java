package com.bazra.usermanagement.request;

import antlr.StringUtils;

public class AgentUpdateRequest {

	
		

		private String firstName;

	    private String lastName;

	    private String companyName;
	    
	    private String username;
	   
	    private String licenceNumber;
	   
	    private String businessLNum;
	    private String roles;
	    private String city;
	    private String subcity;
	    private String woreda;
	    private String companytype;
		private String region;
		private String password;
		 
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public String getRoles() {
			return roles;
		}
		public void setRoles(String roles) {
			this.roles = roles;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getSubcity() {
			return subcity;
		}
		public void setSubcity(String subcity) {
			this.subcity = subcity;
		}
		public String getWoreda() {
			return woreda;
		}
		public void setWoreda(String woreda) {
			this.woreda = woreda;
		}
		public String getCompanytype() {
			return companytype;
		}
		public void setCompanytype(String companytype) {
			this.companytype = companytype;
		}
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
		
		
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
			
		



}
