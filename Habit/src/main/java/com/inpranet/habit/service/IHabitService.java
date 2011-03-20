package com.inpranet.habit.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.inpranet.habit.model.Coordinate;

@WebService
public interface IHabitService {
	@WebMethod
	public void StockData(
			@WebParam(name="userId") int userId,
			@WebParam(name="coordinate") Coordinate coordinate);
	
	@WebMethod
	public String DeduceZone(
			@WebParam(name="userId") int userId, 
			@WebParam(name="planningHorizon") int planningHorizon, 
			@WebParam(name="interest") int interestId);
}
