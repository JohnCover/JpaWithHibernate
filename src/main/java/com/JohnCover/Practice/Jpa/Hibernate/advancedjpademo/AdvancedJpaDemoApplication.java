package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Course;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Review;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Student;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.repository.CourseRepository;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.repository.StudentRepository;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class AdvancedJpaDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedJpaDemoApplication.class, args);
	}
	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public void run(String... args) throws Exception {
		//studentRepo.saveStudentWithPassport();
		//repo.playWithEntityManager();
		//List<Review> reviews = new ArrayList();
		//reviews.add(new Review("5", "Great Hands-on Stuff."));
		//reviews.add(new Review("5", "Hatsoff."));
		//courseRepo.addReviewsForCourse(10003L, reviews);
		//courseRepo.addReviewsForCourse();
		//studentRepo.insertStudentAndCourse();
		studentRepo.insertStudentAndCourse(new Student("Jack"), new Course("Microservices in 100 Steps"));
	}
}
