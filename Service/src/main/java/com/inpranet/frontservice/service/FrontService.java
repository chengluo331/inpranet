package com.inpranet.frontservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.ws.WebEndpoint;

import com.inpranet.frontservice.model.GeoPos;



@Path("/services/")
@Produces("application/json")
@Consumes("application/json")
public class FrontService implements IFrontService {

	@POST
	@Path("/geo")
	@Produces("application/json")
@Consumes("application/json")
	public void serviceGeo(GeoPos pos) {
		// TODO Auto-generated method stub
		
		Logger.getLogger("FrontService").log(Level.INFO, "CA MARCHE : " + pos.getLatitude());
	}
	
	@POST
	@Path("/test2")
	@Produces("application/json")
@Consumes("application/json")
	public void serviceGeo() {
		// TODO Auto-generated method stub
		Logger.getLogger("FrontService").log(Level.INFO, "CA MARCHE test2: ");
	}
	
	@POST
	@Path("/test3")
	@Produces("application/json")
@Consumes("application/json")
	public void serviceGeo(int value) {
		// TODO Auto-generated method stub
		
		Logger.getLogger("FrontService").log(Level.INFO, "CA MARCHE test3 : "  + value);
	}
	
	@GET
	@Path("/test")
	public String serviceTest() {

		Logger.getLogger("FrontService").log(Level.INFO, "IEUGHZEUFGYUZEGFUZEGFYZEFG");
		return "lol";
	}
	

}

