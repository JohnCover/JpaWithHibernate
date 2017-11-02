package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Review {
	@Id
	@GeneratedValue
	private Long id;
	private String rating;
	private String description;
	
	//default no-argument constructor required by JPA
	public Review() {
		
	}
	
	public Review(String rating,String description) {
		this.description = description;
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Review [%s %s]", rating, description);
		
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}
