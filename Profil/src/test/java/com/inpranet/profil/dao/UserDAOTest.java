package com.inpranet.profil.dao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.core.model.User;
import com.inpranet.profil.model.HabitType;

import junit.framework.TestCase;

public class UserDAOTest extends TestCase {
	private IUserDAO userDao ;
	private ClassPathXmlApplicationContext appContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		appContext = new ClassPathXmlApplicationContext("inpranet-data.xml");
		
		// Récupération du bean DAO 
		userDao = (IUserDAO) appContext.getBean("userDao"); 
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		appContext = null;
		userDao = null;
	}
	
	public void testCreate() {
		User u = new User("test1", "test");
		userDao.createUser(u);
	}
}
