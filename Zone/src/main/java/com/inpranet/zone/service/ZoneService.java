package com.inpranet.zone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jws.WebService;

import com.inpranet.zone.dao.IZoneDAO;
import com.inpranet.zone.model.Zone;

// lets CXF know which interface to use when creating WSDL
@WebService(endpointInterface = "com.inpranet.zone.service.IZoneService")
public class ZoneService implements IZoneService {
	
	private IZoneDAO zoneDAO;

	public void setZoneDAO(IZoneDAO zoneDAO) {
		this.zoneDAO = zoneDAO;
	}

	public List<Zone> getZones(double longitude, double latitude) {
		Logger log = Logger.getLogger(this.getClass().getName());
		log.info("Service getZones");
		if (zoneDAO == null) {
			List<Zone> liste = new ArrayList<Zone>();
			liste.add(new Zone("lol"));
			liste.add(new Zone("lol2"));
			return liste;
		}
		log.info("Bonne requete !!!");
		return zoneDAO.getZones(longitude, latitude);
	}

	public String hello(String name) {
		Logger log = Logger.getLogger(this.getClass().getName());
		log.info("Service hello");
		return "hello " + name;
	}
	
	public void test() {
		Logger log = Logger.getLogger(this.getClass().getName());
		log.info("Test OK");
	}
	

}
