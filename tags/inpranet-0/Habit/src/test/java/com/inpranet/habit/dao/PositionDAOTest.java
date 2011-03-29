package com.inpranet.habit.dao;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.habit.model.Position;

import junit.framework.TestCase;

public class PositionDAOTest extends TestCase {
	private IPositionDAO positionDao ;
	private ClassPathXmlApplicationContext appContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		appContext = new ClassPathXmlApplicationContext("inpranet-data.xml");
		
		// Récupération du bean DAO 
		positionDao = (IPositionDAO) appContext.getBean("positionDao"); 
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		appContext = null;
		positionDao = null;
	}
	
	public void testCreate() {
		Position p = new Position(100, 3, 2, new Date());
		try {
			positionDao.createPosition(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
