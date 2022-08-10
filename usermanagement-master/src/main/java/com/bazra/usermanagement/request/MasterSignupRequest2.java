package com.bazra.usermanagement.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class MasterSignupRequest2 {
	
	@ApiModelProperty(value= "This is MasterAgent Phone Number ")
    @NotBlank
    @Size(min = 4, message = "Name must be at least 4 characters")
    private String organizationName;

    @ApiModelProperty(value= "This is MasterAgent username ")
    @NotBlank
    @Size(min = 10)
    private String organizationType;
    
    @ApiModelProperty(value= "This is MasterAgent email ")
    @NotBlank
    @Size(min = 8, message = "Name must be at least 4 characters")
    private String businessSector;

    @ApiModelProperty(value= "This is MasterAgent Region ")
    @NotBlank
    @Size(min = 4, message = "Name must be at least 4 characters")
    private String businessType;
  
    @NotBlank
    @ApiModelProperty(value= "This is MasterAgent city ")
    private String tinNumber;
    
    @NotBlank
    @ApiModelProperty(value= "This is MasterAgent Organization ")
    private BigDecimal capital;
    
    @NotBlank
    @ApiModelProperty(value= "This is MasterAgent Organization ")
    private String region;
    
    @NotBlank
    @ApiModelProperty(value= "This is MasterAgent Organization ")
    private String city;
    
    @NotBlank
    @ApiModelProperty(value= "This is MasterAgent Organization ")
    private String subcity;
    
    @NotBlank
    @ApiModelProperty(value= "This is MasterAgent Organization ")
    private String woreda;
    
    @NotBlank
    @ApiModelProperty(value= "This is MasterAgent Organization ")
    private String specificLocation;
    
    @NotBlank
    @ApiModelProperty(value= "This is MasterAgent Organization ")
    private String houseNumber;
    
    @NotBlank
    @ApiModelProperty(value= "This is MasterAgent Organization ")
    private String phoneNumber;

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public String getBusinessSector() {
		return businessSector;
	}

	public void setBusinessSector(String businessSector) {
		this.businessSector = businessSector;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getTinNumber() {
		return tinNumber;
	}

	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public String getSpecificLocation() {
		return specificLocation;
	}

	public void setSpecificLocation(String specificLocation) {
		this.specificLocation = specificLocation;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
    
    
    
    
    

}
