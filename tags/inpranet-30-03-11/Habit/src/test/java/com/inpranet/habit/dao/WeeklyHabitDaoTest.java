package com.inpranet.habit.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WeeklyHabitDaoTest extends TestCase {
	/** Logger */ 
	static Logger log = Logger.getLogger(WeeklyHabitDAO.class.getName());
	
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
	
	/*public void testWeeklyHabit() {
		int userId = 1002;
		int dayOfWeek = 1;
		int zoneId = 6;
		String myInterest = "commercial";
		Date currentTime = Calendar.getInstance().getTime();
		currentTime.setHours(2);
		int occ1 = weeklyHabitDao.CountOccurrence(dayOfWeek, userId, currentTime, zoneId, myInterest);
		weeklyHabitDao.StockWeeklyHabit(dayOfWeek, userId, currentTime, zoneId, myInterest, occ1);
	}*/
	
	public void testGetIdInterval() {
		log.info("--------------- TestGetIdInterval started --------------");
		Date now = new Date();
		int id = weeklyHabitDao.GetIdInterval(now);
		log.info("--------------- TestGetIdInterval ended --------------");
	}
	
	public void testDeduceIdZoneByInterest() {
		log.info("--------------- TestDeduceIdZoneByInterest started --------------");
		Calendar c = Calendar.getInstance();
		c.set(2011, Calendar.MARCH, 30, 14, 23);
		int idZone;
		try {
			idZone = weeklyHabitDao.DeduceIdZoneByInterest(1, 3, c.getTime());
		} catch (NullPointerException e1) {
			log.error("Champ null, pas de stockage !!!");
			return;
		} catch (SQLException e2) {
			log.error("Erreur de persistence");
			return;
		}
		log.info("id Zone: " + Integer.toString(idZone));
		log.info("--------------- TestDeduceIdZoneByInterest ended --------------");
	}
}
