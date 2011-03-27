package com.inpranet.frontservice.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Zone;


/**
 * Interface proposant des services web en interne
 * Tous les modules qui doivent communiquer avec un autre module doit le faire via 
 * ce service considere comme un BUS
 * @author inpranet team
 *
 */
@WebService
public interface IInternalService {

	/**
	 * Permet de recuperer les zones englobant une position donnee
	 * @param pos position 
	 * @return la liste des zones englobant la position
	 */
	@WebMethod(operationName="getZoneListFromGeoPos")
	public List<Zone> getZoneListFromGeoPos(@WebParam(name="geopos") GeoPos pos);
}
