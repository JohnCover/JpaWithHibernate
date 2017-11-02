package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Course;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Review;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.repository.CourseRepository;

import ch.qos.logback.classic.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryApplicationTests {
	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	EntityManager em;
	@Test
	public void contextLoads() {
	}
	
	@Test
	@Transactional
	public void retriveReviewsForCourse() {
		Course course = courseRepo.findById(10001L);
		log.info("{}", course.getReviews());
		
	}
	
	@Test
	@Transactional
	public void retriveCourseForReviews() {
		Review review = em.find(Review.class, 50001L);
		log.info("{}", review.getCourse());
	}

}
