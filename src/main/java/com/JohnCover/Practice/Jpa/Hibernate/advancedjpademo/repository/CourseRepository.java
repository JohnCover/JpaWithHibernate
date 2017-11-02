package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Course;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Review;

import ch.qos.logback.classic.Logger;

@Repository
@Transactional
public class CourseRepository {
	@Autowired
	EntityManager em;
	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	public Course findById(long id) {
		return em.find(Course.class, id);
	}
	
	
	public void deleteById(long id) {
		em.remove(findById(id));
	}
	
	public Course save(Course course) {
		if(course.getId() == null)
			em.persist(course);
		else
			em.merge(course);
		return course;	
	}
	
	public void playWithEntityManager() {
		Course course1 = new Course("Web Service in 100 Steps");
		em.persist(course1);
		//course1.setName("Web Services in 100 Steps - Updated");		
		Course course2 = findById(10001L);
		course2.setName("JPA in 50 Steps - Updated");
		
		//Course course2 = new Course("Angular Js in 100 Steps");
		//em.persist(course2);
		//em.flush();
		
		//em.clear(); //clear everything em is tracking, following setName() will not persist
		//em.detach(course2);
		
		//course2.setName("Angular Js in 100 Steps - updated");
		
		//em.refresh(course1); //object course 1 is returned to the state in the database
		
		//em.flush();
	}
	
	public void addReviewsForCourse() {
		//get the course 10003
		Course course = findById(10003L);
		log.info("Course.getReviews() -> {}", course.getReviews());
		
		//add 2 reviews to it
		Review review1 = new Review("5", "Great Hands-on Stuff.");
		Review review2 = new Review("5", "Hatsoff.");
		
		review1.setCourse(course);
		course.addReviews(review1);
		
		review2.setCourse(course);
		course.addReviews(review2);
		
		//save it to the database
		em.persist(review1);
		em.persist(review2);
	}
	
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		//get the course 10003
		Course course = findById(courseId);
		log.info("Course.getReviews() -> {}", course.getReviews());
		
		//add reviews to it and save to db
		for(Review review : reviews) {
			review.setCourse(course);
			course.addReviews(review);	
			em.persist(review);
		}

	}
	

}
