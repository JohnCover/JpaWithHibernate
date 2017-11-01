package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NamedQuery(name="query_get_all_courses", query="Select c From Course c")
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(nullable = false)
	private String name;
	
	//default no-argument constructor required by JPA
	public Course() {
		
	}
	
	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Course ["+ name +"]";
	}
}