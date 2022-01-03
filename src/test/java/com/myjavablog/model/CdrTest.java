package com.myjavablog.model;

import static org.junit.Assert.assertEquals;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CdrTest {
	@Autowired
	private TestEntityManager entityManager;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private Cdr clientInfoEntity;
	
	@Before
	public void setUp() {
		clientInfoEntity=new Cdr();
		clientInfoEntity.setName("pradip");
		/*
		 * clientInfoEntity.setCorpId("Test"); clientInfoEntity.setClientName("test");
		 * clientInfoEntity.setAddress1("India");
		 * clientInfoEntity.setAddress2("Kolkata");
		 * clientInfoEntity.setPhoneNo("7890499451");
		 */
		//Mail id, Image name/img 
		
		
	}
	
	@Test
	public void saveSocialMediaSiteFacebook() {
		Cdr savedFacebookData = this.entityManager.persistAndFlush(clientInfoEntity);
		assertEquals("pradip", savedFacebookData.getName());
		//assertEquals("Test", savedFacebookData.getCorpId());
	}

}
