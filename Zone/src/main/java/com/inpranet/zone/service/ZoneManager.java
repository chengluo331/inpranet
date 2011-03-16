package com.inpranet.zone.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.WebEndpoint;

import com.inpranet.zone.dao.IZoneDAO;
import com.inpranet.zone.model.Zone;

/*
@WebService(endpointInterface="com.inpranet.zone.service.ZoneManager", serviceName="ZoneManager", portName="ZoneManagerPort")
public class ZoneManager implements IZoneManager {
	
	private IZoneDAO zoneDAO;

	public void setZoneDAO(IZoneDAO zoneDAO) {
		this.zoneDAO = zoneDAO;
	}

	@WebEndpoint
	public List<Zone> getZones(int lon, int lat) {
		return zoneDAO.getZones(lon, lat);
	}
	

}
*/
