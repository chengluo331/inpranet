package com.inpranet.frontservice.service;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.inpranet.frontservice.model.GeoPos;



public interface IFrontService {

	public void serviceGeo(GeoPos pos);
	
}
