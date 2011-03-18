package com.inpranet.habit.dao;

import java.util.Calendar;
import java.sql.Timestamp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class HabitDaoImplTest extends TestCase{

	private IHabitDAO habitDao ;
	private ClassPathXmlApplicationContext appContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		appContext = new ClassPathXmlApplicationContext("inpranet-data.xml");
		
		// Récupération du bean DAO 
		habitDao = (IHabitDAO) appContext.getBean("habitDao"); 
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		appContext = null;
		habitDao = null;
	}
	
	public int TestStock() {
		Calendar calendar = Calendar.getInstance();
		Timestamp currentTime = new Timestamp(calendar.getTime().getTime());
		int returnCode = habitDao.StockRawHabit(1001, currentTime , 4.8349, 45.764);
		return returnCode;
	}
	
	
}
