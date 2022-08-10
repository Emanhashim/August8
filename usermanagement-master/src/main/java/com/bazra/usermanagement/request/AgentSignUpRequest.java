package com.bazra.usermanagement.request;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AgentSignUpRequest {

	
	    @ApiModelProperty(value= "This is Agent first name ")
	    @NotBlank
	    @Size(min = 4, message = "Name must be at least 4 characters")
	    private String firstName;

	    @ApiModelProperty(value= "This is Agent father name ")
	    @NotBlank
	    @Size(min = 4, message = "Name must be at least 4 characters")
	    private String lastName;

	    @ApiModelProperty(value= "This is Agent Company name ")
	    @NotBlank
	    @Size(min = 4, message = "Name must be at least 4 characters")
	    private String companyName;
	    @ApiModelProperty(value= "This is Agent Phone Number ")
	    @NotBlank
	    @Size(min = 10)
	    private String username;
	    @NotBlank
	    @ApiModelProperty(value= "This is Agent License Number ")
	    private String licenceNumber;
	    @NotBlank
	    @ApiModelProperty(value= "This is Agent Business License Number ")
	    private String businessLNum;
	    private String role;
	    @ApiModelProperty(value= "This is users Password ")
	    @NotBlank
	    @Size(min = 6, message = "Password should be atlease 6 characters")
	    private String password;
	    @NotBlank
	    @Size(min = 6, message = "Password must be at least 6 characters")
	    private String confirmPassword;
	    
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
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
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
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getPassword() {
			
			return password;
		}
		public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	    
	    
	    
	    
}
