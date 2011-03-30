package com.inpranet.frontservice.service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Zone;
import com.inpranet.core.ws.zone.IZoneService;


// lets CXF know which interface to use when creating WSDL
@WebService(endpointInterface = "com.inpranet.frontservice.service.IInternalService")
public class InternalService implements IInternalService {

	private IZoneService zoneService;
	
	public InternalService() {
		// chargement des services web
		zoneService =  (IZoneService)context.getBean("zoneService");
	}
	
	// TODO: changer ce systeme avec autowire=byName dans les beans
	private static ClassPathXmlApplicationContext    context =
	    new ClassPathXmlApplicationContext(new String[] {"inpranet-service.xml"});
	
	@Override
	public List<Zone> getZoneListFromGeoPos(GeoPos pos) {
		return zoneService.getZoneListFromPos(pos.getLongitude(), pos.getLatitude());
	}
}
