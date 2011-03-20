package com.inpranet.habit.service;

import javax.jws.WebService;

import com.inpranet.habit.model.Coordinate;

@WebService(endpointInterface = "com.inpranet.habit.service.IHabitService")
		
public class HabitServiceImp implements IHabitService {
	
	public void StockData(int userId, Coordinate coordinate) {
		System.out.println("stock raw data");
	}
	
	public String DeduceZone(int userId, int planningHorizon, int interestId) {
		System.out.println("deduce destination zone");
		return "idZone deduced";
	}
}
