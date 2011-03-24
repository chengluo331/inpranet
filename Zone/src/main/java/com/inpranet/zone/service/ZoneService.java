package com.inpranet.zone.service;

import java.util.Collection;
import java.util.logging.Logger;

import javax.jws.WebService;

import com.inpranet.core.model.Zone;
import com.inpranet.zone.dao.IZoneDAO;


// lets CXF know which interface to use when creating WSDL
@WebService(endpointInterface = "com.inpranet.zone.service.IZoneService")
public class ZoneService implements IZoneService {
	
	private IZoneDAO zoneDAO;

	public void setZoneDAO(IZoneDAO zoneDAO) {
		this.zoneDAO = zoneDAO;
	}
	
	public ZoneService() {
		super();
	}

	public Collection<Zone> getZoneListFromPos(double longitude, double latitude) {
		Logger log = Logger.getLogger(this.getClass().getName());
		log.info("Service getZones");

		if (zoneDAO == null) {
			return null;
			
		}
		log.info("Bonne requete !!!");
		return zoneDAO.getZones(longitude, latitude);
	}
	

}
