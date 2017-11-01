package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
public class JpqlTests {

	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_basic() {
		TypedQuery<Course> query = 
				em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		
		log.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_typed() {
		TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);
		List<Course> resultList = query.getResultList();
		
		log.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createQuery("select c from Course c where name like '%100 Steps'", Course.class);
		List<Course> resultList = query.getResultList();
		
		log.info("select c from Course c where name like '%100 Steps' -> {}", resultList);
	}

}
