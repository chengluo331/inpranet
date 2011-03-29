package com.inpranet.zone.service;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.inpranet.core.model.Zone;


@WebService
public interface IZoneService {

	@WebMethod(operationName="getZoneListFromPos")
	public Collection<Zone> getZoneListFromPos(@WebParam(name="longitude") double longitude,  @WebParam(name="latitude") double latitude);
}
