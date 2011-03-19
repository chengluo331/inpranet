package com.inpranet.frontservice.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.inpranet.frontservice.model.GeoPos;

@Path("/services/")
public class FrontService implements IFrontService {

	@POST
	@Path("/geo")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public void serviceGeo(GeoPos pos) {
		// TODO Auto-generated method stub

		Logger.getLogger("FrontService").log(Level.INFO,
				"CA MARCHE : " + pos.getLatitude());
	}
}
