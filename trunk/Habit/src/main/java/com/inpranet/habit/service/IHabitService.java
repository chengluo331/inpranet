package com.inpranet.habit.service;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Interest;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;


@WebService
public interface IHabitService {
	@WebMethod
	public void StockData(
			@WebParam(name="user") User user,
			@WebParam(name="geoPos") GeoPos geoPos,
			@WebParam(name="zones") Collection<Zone> zones);
	
	@WebMethod
	public Collection<Zone> DeduceZone(
			@WebParam(name="user") User user, 
			@WebParam(name="planningHorizon") int planningHorizon, 
			@WebParam(name="interests") Collection<Interest> interests);
}
