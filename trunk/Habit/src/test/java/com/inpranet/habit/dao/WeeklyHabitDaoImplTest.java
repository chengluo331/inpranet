package com.inpranet.habit.dao;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WeeklyHabitDaoImplTest extends TestCase {

	private IWeeklyHabitDAO weeklyHabitDao;
	private ClassPathXmlApplicationContext appContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		appContext = new ClassPathXmlApplicationContext("inpranet-data.xml");
		
		// Récupération du bean DAO 
		weeklyHabitDao = (IWeeklyHabitDAO) appContext.getBean("weeklyHabitDao");
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		appContext = null;
		weeklyHabitDao = null;
	}
	
	public void testWeeklyHabit() {
		int userId = 1002;
		int dayOfWeek = 1;
		int zoneId = 6;
		String myInterest = "commercial";
		Date currentTime = Calendar.getInstance().getTime();
		currentTime.setHours(2);
		int occ1 = weeklyHabitDao.CountOccurrence(dayOfWeek, userId, currentTime, zoneId, myInterest);
		weeklyHabitDao.StockWeeklyHabit(dayOfWeek, userId, currentTime, zoneId, myInterest, occ1);
	}
}
