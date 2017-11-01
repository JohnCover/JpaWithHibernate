package com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.AdvancedJpaDemoApplication;
import com.JohnCover.Practice.Jpa.Hibernate.advancedjpademo.entity.Course;

import ch.qos.logback.classic.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AdvancedJpaDemoApplication.class)
public class NativeQueriesTest {

	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void native_queries_basic() {
		Query query = em.createNativeQuery("select * from course", Course.class);
		List resultList = query.getResultList();
		log.info("Select * from Course -> {}", resultList);
	}
	
	@Test
	public void native_queries_with_parameter() {
		Query query = em.createNativeQuery("select * from course where id=?", Course.class);
		query.setParameter(1, 10001L);
		
		List resultList = query.getResultList();
		log.info("Select * from Course where id=? -> {}", resultList);
	}
	
	//No way to do mass update of rows in JPA, so native query is required
	@Test
	@Transactional
	@DirtiesContext
	public void native_queries_to_update() {
		Query query = em.createNativeQuery("update course set last_updated_date=sysdate()", Course.class);
		int numOfRowsUpdated = query.executeUpdate();
		log.info("update course, num of rows affected -> {}", numOfRowsUpdated);
	}
	

}
