package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Student {
	@Id
	@GeneratedValue
	private Long id;

	
	@Column(nullable = false)
	private String name;
	
	@OneToOne(fetch=FetchType.LAZY)
	protected Passport passport;
	
	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	//default no-argument constructor required by JPA
	public Student() {
		
	}
	
	public Student(String name) {
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
		return "Student ["+ name +"]";
	}
}
