package com.bazra.usermanagement.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters.BigDecimalConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import antlr.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Bemnet
 * @version 4/2022
 *
 */

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "userInfo")

public class UserInfo implements UserDetails {
	
    private static final long serialVersionUID = 1L;
    private UserInfo userInfo;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    private String firstName;
    private String motherName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String username;
    private String email;
    private String birthDay;
    private String photo;
    private String kebeleID;
    private String roles;
    @Enumerated(EnumType.STRING)
    private Levels levels ;
    private Boolean locked = false;
    private Boolean enabled = true;
    private String resetPasswordToken;
    private String country;
    private String gender;
    private String region;
    private String city;
    private String subCity;
    private String woreda;
    private String houseNo;
    private String userType;
    private String idType;
    private String idNumber;
    
    
    
//    public UserInfo() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

    public UserInfo(String password, String username, String resetpasswordtoken) {

        this.password = password;
        this.username = username;
        this.resetPasswordToken = resetpasswordtoken;

    }

    public UserInfo(String firstName, String lastName, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;

    }

    public UserInfo(String firstName, String lastName, String password, String username, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.gender = gender;

    }

//    public UserInfo(String roles , int id, String password, String username) {
//        this.id = id;
//        this.roles = roles;
//        this.password = password;
//        this.username = username;
//    }


	public String getUserType() {
		return userType;
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

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getBirthday() {
        return birthDay;
    }

    public void setBirthday(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

	public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getSubCity() {
        return subCity;
    }

    public void setSubCity(String subCity) {
        this.subCity = subCity;
    }

    public String getWoreda() {
        return woreda;
    }

    public void setWoreda(String woreda) {
       this.woreda = woreda;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
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

    public void setLastname(String lastName) {
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String  getRoles() {
        return roles;
      }

      public void setRoles(String  roles) {
        this.roles = roles;
      }
    public Collection<? extends GrantedAuthority> getAuthorities2() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(levels.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//    	String roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        System.out.println(roles);
        
        authorities.add(new SimpleGrantedAuthority(roles));
        
        return authorities;
    }



}
