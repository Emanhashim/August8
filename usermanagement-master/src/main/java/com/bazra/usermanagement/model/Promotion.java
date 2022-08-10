package com.bazra.usermanagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "promotion")
public class Promotion {

	public Promotion() {
	}

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
	
	@Column(unique=true)
	private String title;
	
	private String picture;
	private String description;
    private LocalDate expirationDate;
    private boolean status;
    
    
	public Promotion(String title, String picture, String description, LocalDate expirationDate, boolean status) {
		super();
		this.title = title;
		this.picture = picture;
		this.description = description;
		this.expirationDate = expirationDate;
		this.status = status;
	}

	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
