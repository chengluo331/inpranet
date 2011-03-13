package com.inpranet.zone.dao;

import java.util.List;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.zone.model.Zone;

public class ZoneDaoImplTest  extends TestCase { 
	
	private IZoneDAO zoneDao ;
	private ClassPathXmlApplicationContext appContext;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		appContext = new ClassPathXmlApplicationContext("inpranet-data.xml");
		
		// Récupération du bean DAO 
		zoneDao = (IZoneDAO) appContext.getBean("zoneDao"); 
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		appContext = null;
		zoneDao = null;
	}
		
	public void testTest(){
		List<Zone> zones = zoneDao.getZones(2, 3);
			
		Logger log = Logger.getLogger(this.getClass().getName());
		for (Zone zone : zones) {
			log.info(zone.getId());
		}
		
		assertEquals(4, 4);
	}
}


