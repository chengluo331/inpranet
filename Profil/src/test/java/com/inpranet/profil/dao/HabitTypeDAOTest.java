package com.inpranet.profil.dao;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.profil.model.HabitType;

import junit.framework.TestCase;

public class HabitTypeDAOTest extends TestCase {
	private IHabitTypeDAO habitTypeDao ;
	private ClassPathXmlApplicationContext appContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		appContext = new ClassPathXmlApplicationContext("inpranet-data.xml");
		
		// Récupération du bean DAO 
		habitTypeDao = (IHabitTypeDAO) appContext.getBean("habitTypeDao"); 
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		appContext = null;
		habitTypeDao = null;
	}
	
	public void testCreate() {
		HabitType h = new HabitType(100, 0, 0, 0, 0, 0, 0, 0);
		habitTypeDao.createHabitType(h);
	}
}
