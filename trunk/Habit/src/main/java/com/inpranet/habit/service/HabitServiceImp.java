package com.inpranet.habit.service;

import java.util.logging.Logger;

import javax.jws.WebService;


import com.inpranet.habit.model.Coordinate;

@WebService(endpointInterface = "com.inpranet.habit.service.IHabitService")		
public class HabitServiceImp implements IHabitService {
		
	/** Logger */
	static Logger log = Logger.getLogger(HabitServiceImp.class.getName());
		
	public void StockData(int userId, Coordinate coordinate) {
		log.info("stock raw data");
	}
	
	public String DeduceZone(int userId, int planningHorizon, int interestId) {
		log.info("deduce destination zone");
		return "idZone deduced";
	}
}
