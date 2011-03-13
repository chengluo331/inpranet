package com.inpranet.zone.service;

import java.util.ArrayList;
import java.util.List;

import com.inpranet.zone.dao.IZoneDAO;
import com.inpranet.zone.model.Zone;


public class ZoneManager implements IZoneManager {
	
	private IZoneDAO zoneDAO;

	public void setZoneDAO(IZoneDAO zoneDAO) {
		this.zoneDAO = zoneDAO;
	}

	public List<Zone> getZones(int lon, int lat) {
		return zoneDAO.getZones(lon, lat);
	}
	

}
