package com.bazra.usermanagement.request;

import com.bazra.usermanagement.model.Roles;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Bemnet
 * @version 4/2022
 *
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class SignUpRequest {
    @ApiModelProperty(value= "This is users first name ")
    @NotBlank
    @Size(min = 4, message = "Name must be at least 4 characters")
    private String firstName;

    @ApiModelProperty(value= "This is users father name ")
    @NotBlank
    @Size(min = 4, message = "Name must be at least 4 characters")
    private String lastName;

    @ApiModelProperty(value= "This is users Password ")
    @NotBlank
    @Size(min = 6, message = "Password should be atlease 6 characters")
    private String password;

    @ApiModelProperty(value= "This is users Phone Number ")
    @NotBlank
    @Size(min = 10)
    private String username;

    @ApiModelProperty(value= "This is Users Email ")
    @NotBlank(message = "Enter your email")
    @Column(unique = true)
    @Email
    private String email;
    private String role;
    @ApiModelProperty(value= "This is Users BirthDate ")
    private String birthDay;
    @ApiModelProperty(value= "This is users Gender ")
    private String gender;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String confirmPassword;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthday) {
        this.birthDay = birthday;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String password2) {
        this.confirmPassword = password2;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    @Override
    public String toString() {
        return "SignUpRequest [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
                + ",  username=" + username + ", confirmPassword=" + confirmPassword + "]";
    }

}
