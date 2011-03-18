package com.inpranet.zone.service;

import java.util.List;

import com.inpranet.zone.model.Zone;


public interface IZoneManager {
	public List<Zone> getZones(int lon, int lat);
}
