package com.bazra.usermanagement.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class CreatePromotionRequest {
	@NotBlank(message = "Enter Promotion Title")
    private String title;
	@NotBlank(message = "Enter Promotion Description")
	private String description;
	@NotBlank(message = "Enter Promotion Picture")
	private MultipartFile picture;
	@NotBlank(message = "Enter Promotion Expiration Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expirationDate;
	@NotBlank(message = "Enter Promotion Status")
	private boolean status;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
