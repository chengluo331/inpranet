package com.inpranet.habit.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.zone.model.Zone;

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
	
	public void testStock() {
		
		//stocker une localisation brute
		int userId = 1002;
		double longitude = 4.8349;
		double latitude = 45.764;
		Date currentTime = Calendar.getInstance().getTime();
		int returnCode = habitDao.StockRawHabit(userId, currentTime , longitude, latitude);
		assertEquals(returnCode, 1);
		
		//en reception de zoneId, stocker dans la table geo_tempo_events sans timeOut puis ajouter le timeOut
		int zoneId = 6;
		String myInterest = "commercial";
		List<Zone> newZones = new ArrayList<Zone>();
		Zone z1 = new Zone(5);
		newZones.add(z1);
		//il faut ajouter un test pour ne pas ajouter une ligne dans la table geo_tempo_events
		habitDao.StockEventTimeIn(userId, currentTime, zoneId, myInterest);
		
		List<Zone> diffZones = habitDao.CompareCurrentAndNewZones(userId,newZones);
		for(int i=0;i<diffZones.size();i++){
			habitDao.StockEventTimeOut(userId, Calendar.getInstance().getTime(),diffZones.get(i).getId());
		}
		
//		habitDao.DetermineCurrentZone(userId);
	};
	
	
	
}

