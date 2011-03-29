package com.inpranet.zone.service;

import java.util.Collection;
import java.util.logging.Logger;

import javax.jws.WebService;

import com.inpranet.core.model.Zone;
import com.inpranet.zone.dao.IZoneDAO;


// lets CXF know which interface to use when creating WSDL
@WebService(endpointInterface = "com.inpranet.zone.service.IZoneService")
public class ZoneService implements IZoneService {
	
	/** Logger */
	private static Logger log = Logger.getLogger(ZoneService.class
			.getName());
	
	private IZoneDAO zoneDAO;

	public void setZoneDAO(IZoneDAO zoneDAO) {
		this.zoneDAO = zoneDAO;
	}
	
	public ZoneService() {
		super();
	}

	public Collection<Zone> getZoneListFromPos(double longitude, double latitude) {
		log.info("Appel du service getZones");

		log.info("Execution de la requete dans postgresql");
		Collection<Zone> zones = zoneDAO.getZones(longitude, latitude);
		
		log.info(zones.size() + " zones correspondent à cette coordonnee");
		
		return zones; 
	}
	

}
