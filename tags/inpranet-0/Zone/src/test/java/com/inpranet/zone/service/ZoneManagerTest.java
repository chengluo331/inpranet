package com.inpranet.zone.service;

import java.util.Collection;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.core.model.Zone;

public class ZoneManagerTest  extends TestCase { 
	
	private IZoneService zoneManager ;
	private ClassPathXmlApplicationContext appContext;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		appContext = new ClassPathXmlApplicationContext("appContext.xml");
		
		// Récupération du bean DAO 
		zoneManager = (IZoneService) appContext.getBean("zoneService"); 
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		appContext = null;
		zoneManager = null;
	}
		
	public void testGetZoneListFromPos(){
		Collection<Zone> zones = zoneManager.getZoneListFromPos(4.8567, 45.7607);
		
		/* 2 zones englobent ce point */
		assertEquals(zones.size(), 2);
	}
}



