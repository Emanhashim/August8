package com.bazra.usermanagement.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl;

import com.bazra.usermanagement.model.Levels;
import com.bazra.usermanagement.model.Transaction;
import com.bazra.usermanagement.model.UserInfo;

import net.bytebuddy.asm.Advice.This;

public class UserResponse {
	private String message;
	private Integer user_id;
	private String username;
    private String type;
    private String birthDay;
	private String city;
	private String email;
	private Boolean enabled;
    private String firstName;
    private String motherName;
    private String houseNo;
    private String gender;
	private String lastName;
	private Levels levels;
	private Boolean locked;
    private String region;
    private String roles;
	private String subCity;
	private String userType;
	private String woreda;
    private String photo;
    private String kebeleID;
    private String idType;
    private String idNumber;
    private List<UserInfo> userInfos = new ArrayList<>();
//    private UserInfo userInfo = new UserInfo();
	
	public long getUser_id() {
		return user_id;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Levels getLevels() {
		return levels;
	}
	public void setLevels(Levels levels) {
		this.levels = levels;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
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
	public String getSubCity() {
		return subCity;
	}
	public void setSubCity(String subCity) {
		this.subCity = subCity;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getWoreda() {
		return woreda;
	}
	public void setWoreda(String woreda) {
		this.woreda = woreda;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getKebeleID() {
		return kebeleID;
	}
	public void setKebeleID(String kebeleID) {
		this.kebeleID = kebeleID;
	}
    
    public UserResponse(List<UserInfo> userInfoss) {
    	
    	this.userInfos
        = new ArrayList<>(userInfoss);
    	
    }
    
    public UserResponse(UserInfo userInfoss) {

    	this.user_id=userInfoss.getId();
    	this.birthDay=userInfoss.getBirthday();
    	this.city=userInfoss.getCity();
    	this.email=userInfoss.getEmail();
    	this.firstName=userInfoss.getFirstName();
    	this.lastName=userInfoss.getLastName();
    	this.motherName=userInfoss.getMotherName();
    	this.gender=userInfoss.getGender();
    	this.kebeleID=userInfoss.getKebeleID();
    	this.houseNo=userInfoss.getHouseNo();
    	this.levels=userInfoss.getLevels();
    	this.enabled=userInfoss.getEnabled();
    	this.locked=userInfoss.getLocked();
    	this.photo=userInfoss.getPhoto();
    	this.region=userInfoss.getRegion();
    	this.roles=userInfoss.getRoles();
    	this.subCity=userInfoss.getSubCity();
    	this.type=userInfoss.getUserType();
    	this.username=userInfoss.getUsername();
    	this.idNumber=userInfoss.getIdNumber();
    	this.idType= userInfoss.getIdType();
    }
    public UserResponse(String message) {

    	this.message=message;
    }
    
}
