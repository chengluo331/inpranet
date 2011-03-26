package com.inpranet.frontservice.orchestration;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;
import com.inpranet.core.ws.habit.IHabitService;
import com.inpranet.core.ws.zone.IZoneService;



public class BusinessProcessor implements IBusinessProcessor {

	@Override
	public void receiveRawPositions(final User user, final GeoPos pos) {
		// call authent ?
		// call service from zone
		// call service from habit
		ClassPathXmlApplicationContext     context =
		    new ClassPathXmlApplicationContext(new String[]     {"inpranet-service.xml"});
		IZoneService service =     (IZoneService)context.getBean("serviceZone");
		
		Logger.getLogger("BP").log(Level.INFO,
				"AVANT APPEL SERVICE");
		
		Collection<Zone> zones = service.getZoneListFromPos(pos.getLongitude(), pos.getLatitude());
		
		Logger.getLogger("BP").log(Level.INFO,
				"CA MARCHE : " + zones.size());
		
		IHabitService habitService =     (IHabitService)context.getBean("serviceHabit");
		
		
		habitService.stockData(user, pos, (List<Zone>)zones);
		
	}

	/*
	@Override
	public Collection<Document> receiveDocumentOrder(User user) {
		// call authent
		// call service from indexation
		return null;
	}*/

}
