package com.bazra.usermanagement.model;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "masteragentInfo")
public class MasterAgentInfo  implements UserDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Agent_id")
    private int id;
	private String userName;
	private String phoneNumber;
	private String email;
	private String region;
	private String city;
	private String organizationType;
	private String roles;
	private String organizationName;
	private String businessSector;
	private String businessType;
	private String tinNumber;
	private String subcity;
	private String woreda;
	private String specificLocation;
	private String houseNumber;
	private BigDecimal capital;
	private String tinDocument;
	private String treadDocument;
	private String password;
	
//	public MasterAgentInfo() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
	public MasterAgentInfo(String phone, String username2,String password,String email2, String region2, String city2,
			String organizationType2) {
		
		this.phoneNumber=phone;
		this.userName=username2;
		this.email=email2;
		this.region=region2;
		this.city=city2;
		this.organizationType=organizationType2;
		this.password=password;
	}
	
	
	
	



	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	

	public String getTinDocument() {
		return tinDocument;
	}



	public void setTinDocument(String tinDocument) {
		this.tinDocument = tinDocument;
	}



	public String getTreadDocument() {
		return treadDocument;
	}



	public void setTreadDocument(String treadDocument) {
		this.treadDocument = treadDocument;
	}



	public String getOrganizationName() {
		return organizationName;
	}



	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
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



	public BigDecimal getCapital() {
		return capital;
	}



	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public String getUserName() {
//		return userName;
//	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
