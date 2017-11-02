package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.repository;

import javax.persistence.EntityManager;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Course;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Passport;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Student;

import ch.qos.logback.classic.Logger;

@Repository
@Transactional
public class StudentRepository {
	@Autowired
	EntityManager em;
	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	public Student findById(long id) {
		return em.find(Student.class, id);
	}
	
	public void deleteById(long id) {
		em.remove(findById(id));
	}
	
	public Student save(Student Student) {
		if(Student.getId() == null)
			em.persist(Student);
		else
			em.merge(Student);
		return Student;	
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport); //make sure to persist passport before student so you 
		                      //can set up relation without transient property exception 
		                      //due to Students FK dependency on Passport.id
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
	}
	
	public void playWithEntityManager() {
		Student Student1 = new Student("Web Service in 100 Steps");
		em.persist(Student1);
		//Student1.setName("Web Services in 100 Steps - Updated");		
		Student Student2 = findById(10001L);
		Student2.setName("JPA in 50 Steps - Updated");	
		
	}
	
	public void insertStudentAndCourse() {
		Student student = new Student("Jack");
		Course course = new Course("Microservices in 100 steps");
		em.persist(student);
		em.persist(course);
		
		student.addCourse(course);
		course.addStudent(student);
		em.persist(student);
		
	}
	
	public void insertStudentAndCourse(Student student, Course course) {
		student.addCourse(course);
		course.addStudent(student);
		em.persist(student);
		em.persist(course);
		
	}
}
