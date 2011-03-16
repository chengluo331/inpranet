package com.inpranet.zone.service;

import java.util.List;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.zone.model.Zone;
/*
public class ZoneManagerTest  extends TestCase { 
	
	private IZoneManager zoneManager ;
	private ClassPathXmlApplicationContext appContext;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		appContext = new ClassPathXmlApplicationContext("inpranet-service.xml");
		
		// Récupération du bean DAO 
		zoneManager = (IZoneManager) appContext.getBean("zoneManager"); 
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		appContext = null;
		zoneManager = null;
	}
		
	public void testTest(){
		List<Zone> zones = zoneManager.getZones(2, 3);
			
		Logger log = Logger.getLogger(this.getClass().getName());
		for (Zone zone : zones) {
			log.info(zone.getId());
		}
		
		assertEquals(4, 4);
	}
}
*/


