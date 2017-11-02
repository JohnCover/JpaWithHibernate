package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.repository;


import javax.persistence.EntityManager;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.AdvancedJpaDemoApplication;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Course;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Passport;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Student;

import ch.qos.logback.classic.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AdvancedJpaDemoApplication.class)
public class StudentRepositoryTest {

	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository repo;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional //Must add @Transactional to maintain session for getPassport() 
				   //to not throw exception when using lazy fetching
	//any 1 to 1 relationship will be an eagerly fetched. i.e. passport is fetched automatically
	//unless fetching is specified in the relation annotation
	public void retrieveStudentAndAssociatedPassport() {
		Student student = em.find(Student.class, 20001L);
		log.info("Student -> {}", student);
		log.info("Passport -> {}", student.getPassport());
		
	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		
		log.info("Passport -> {}", passport);
		log.info("Student -> {}", passport.getStudent());
		
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		log.info("Student -> {}", student);
		log.info("courses -> {}", student.getCourses());	
		
	}
	
	@Test
	@Transactional
	public void retrieveCoursesAndStudents() {
		Course course = em.find(Course.class, 10001L);
		log.info("Student -> {}", course);
		log.info("courses -> {}", course.getStudents());	
		
	}
	
	@Test
	@Transactional 
	public void someTest() {
		//Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		//Persistence Context (student)
		
		//Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		//Persistence Context(student,passport)
		
		//Database Operation 3 - update passport
		passport.setNumber("E123457");
		//Persistence Context(student,passport++)
		
		//Database Operation 4 - update student
		student.setName("Ranga - updated");
		//Persistence Context(student++,passport++)
	}

	
	
	
	
}
