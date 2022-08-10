package com.bazra.usermanagement.request;

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
public class MasterSignupRequest {

	
	    @ApiModelProperty(value= "This is MasterAgent Phone Number ")
	    @NotBlank
	    @Size(min = 4, message = "Name must be at least 4 characters")
	    private String phone;

	    @ApiModelProperty(value= "This is MasterAgent username ")
	    @NotBlank
	    @Size(min = 10)
	    private String username;
	    
	    @ApiModelProperty(value= "This is MasterAgent email ")
	    @NotBlank
	    @Size(min = 8, message = "Name must be at least 4 characters")
	    private String email;

	    @ApiModelProperty(value= "This is MasterAgent Region ")
	    @NotBlank
	    @Size(min = 4, message = "Name must be at least 4 characters")
	    private String region;
	  
	    @NotBlank
	    @ApiModelProperty(value= "This is MasterAgent city ")
	    private String city;
	    
	    @NotBlank
	    @ApiModelProperty(value= "This is MasterAgent Organization ")
	    private String organizationType;

	    @ApiModelProperty(value= "This is users Password ")
	    @NotBlank
	    @Size(min = 6, message = "Password should be atlease 6 characters")
	    private String password;
	    
		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		
		

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public String getOrganizationType() {
			return organizationType;
		}

		public void setOrganizationType(String organizationType) {
			this.organizationType = organizationType;
		}
	    
	    
	    
	    
	    
	    
	    
}