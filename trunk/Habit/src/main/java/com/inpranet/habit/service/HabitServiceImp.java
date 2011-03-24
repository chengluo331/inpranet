package com.inpranet.habit.service;

import java.util.Collection;
import java.util.logging.Logger;

import javax.jws.WebService;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Interest;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;


@WebService(endpointInterface = "com.inpranet.habit.service.IHabitService")		
public class HabitServiceImp implements IHabitService {
		
	/** Logger */
	static Logger log = Logger.getLogger(HabitServiceImp.class.getName());
	
	public void StockData(User user, GeoPos geoPos, Collection<Zone> zones) {
		log.info("stock raw data");
		// TODO Auto-generated method stub
	}

	public Collection<Zone> DeduceZone(User user, int planningHorizon,
			Collection<Interest> interests) {
		log.info("deduction");
		// TODO Auto-generated method stub
		return null;
	}
}
