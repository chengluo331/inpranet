package com.inpranet.frontservice.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.inpranet.frontservice.model.GeoPos;
import com.inpranet.zone.service.IZoneManager;
import com.inpranet.zone.service.IZoneService;
import com.inpranet.zone.service.Zone;

@Path("/services/")
public class FrontService implements IFrontService {

	@POST
	@Path("/geo")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public void serviceGeo(GeoPos pos) {
		
		Logger.getLogger("FrontService").log(Level.INFO,
				"CA MARCHE : " + pos.getLatitude());
		
		ClassPathXmlApplicationContext     context =
		    new ClassPathXmlApplicationContext(new String[]     {"inpranet-service.xml"});
		IZoneService zoneManager =     (IZoneService)context.getBean("serviceZone");
		
		List<Zone> zones = zoneManager.getZonesFromPos(2, 3);
		
		Logger log = Logger.getLogger(this.getClass().getName());
		for (Zone zone : zones) {
			log.info(zone.toString());
		}
	}

	@GET
	@Path("/json")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public GeoPos testJSON() {
		return new GeoPos(4, 5, new Date());
	}

}
