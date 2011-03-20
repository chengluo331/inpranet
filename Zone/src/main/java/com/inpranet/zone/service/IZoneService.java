package com.inpranet.zone.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.inpranet.zone.model.Zone;


@WebService
public interface IZoneService {

	@WebMethod(operationName="getZonesFromPos")
	public List<Zone> getZones(@WebParam(name="longitude") double longitude,  @WebParam(name="latitude") double latitude);
	public String hello(String name);
	public void test();
}
