package com.inpranet.zone.dao;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.inpranet.zone.model.Zone;


@WebService
public interface IZoneDAO {

	@WebMethod
	List<Zone> getZones(int lon , int lat);
}
