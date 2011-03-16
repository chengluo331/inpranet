package com.inpranet.zone.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.inpranet.zone.model.Zone;


@WebService(name="IZoneManager",targetNamespace="http://helloworldwebservice.lisi.ensma.fr/")
@SOAPBinding(style=Style.RPC, use=Use.LITERAL)
public interface IZoneManager {
	@WebMethod(operationName="getZones")
	@WebResult(name="zoneManagerResult")
	public List<Zone> getZones(@WebParam(name = "lon") int lon, @WebParam(name = "lon") int lat);
}
