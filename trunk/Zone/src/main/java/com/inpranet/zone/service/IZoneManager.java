package com.inpranet.zone.service;

import java.util.List;

import javax.jws.WebService;

import com.inpranet.zone.model.Zone;


@WebService
public interface IZoneManager {

	public List<Zone> getZones(double longitude,  double latitude);
	public String hello(String name);
	public void test();
}
