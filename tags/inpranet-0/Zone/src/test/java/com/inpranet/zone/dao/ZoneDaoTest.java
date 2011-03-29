package com.inpranet.zone.dao;

import java.util.Collection;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.core.model.Zone;

public class ZoneDaoTest  extends TestCase { 
	
	private IZoneDAO zoneDao ;
	private ClassPathXmlApplicationContext appContext;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		appContext = new ClassPathXmlApplicationContext("appContext.xml");
		
		// Récupération du bean DAO 
		zoneDao = (IZoneDAO) appContext.getBean("zoneDao"); 
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		appContext = null;
		zoneDao = null;
	}
		
	public void testGetZones(){
		Collection<Zone> zones = zoneDao.getZones(4.8567, 45.7607);
		
		/* 2 zones englobent ce point */
		assertEquals(zones.size(), 2);
	}
}



