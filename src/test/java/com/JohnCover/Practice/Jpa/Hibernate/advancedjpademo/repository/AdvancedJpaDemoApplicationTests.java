package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.AdvancedJpaDemoApplication;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Course;

import ch.qos.logback.classic.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AdvancedJpaDemoApplication.class)
public class AdvancedJpaDemoApplicationTests {

	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repo;
	
	
	@Test
	public void findById_basic() {
		Course course = repo.findById(10001L);
		
		assertEquals("JPA in 50 Steps", course.getName());
	}
	
	@Test
	@DirtiesContext
	public void deleteById_basic() {
		repo.deleteById(10001L);
		assertNull(repo.findById(10001L));
	}
	
	@Test
	@DirtiesContext
	public void save_basic() {
		//get a course
		Course course = repo.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
		//update details
		course.setName("JPA in 50 Steps - Updated");
		repo.save(course);
		//check value
		Course course2 = repo.findById(10001L);
		assertEquals("JPA in 50 Steps - Updated", course2.getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repo.playWithEntityManager();
	}


}
