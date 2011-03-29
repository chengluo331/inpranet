package com.inpranet.zone.dao;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.inpranet.core.model.Zone;




@WebService
public interface IZoneDAO {

	@WebMethod
	Collection<Zone> getZones(double longitude , double latitude);
}
