package com.inpranet.habit.service;

import java.util.Date;

import javax.jws.WebService;

@WebService(targetNamespace = "http://service.habit.inpranet.com/", endpointInterface = "com.inpranet.habit.service.StockDataSEI", portName = "StockDataServicePort", serviceName = "StockDataServiceService")
public class StockDataService implements StockDataSEI {
	
	
	/** Stocker les donnees geo-temporels
	 * 
	 * @param userId
	 * @param longitude
	 * @param latitude
	 * @param time
	 */
	public boolean StockData(long userId, double longitude, double latitude, Date time) {
		return true;
	}
}
