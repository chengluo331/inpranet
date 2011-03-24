package com.inpranet.frontservice.orchestration;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.habit.service.GeoPos;
import com.inpranet.habit.service.User;
import com.inpranet.zone.service.IZoneService;
import com.inpranet.zone.service.Zone;



public class BusinessProcessor implements IBusinessProcessor {

	@Override
	public void receiveRawPositions(final User user, final GeoPos pos) {
		// call authent ?
		// call service from zone
		// call service from habit
		ClassPathXmlApplicationContext     context =
		    new ClassPathXmlApplicationContext(new String[]     {"inpranet-service.xml"});
		IZoneService service =     (IZoneService)context.getBean("serviceZone");
		
		Collection<Zone> zones = service.getZonesFromPos(pos.getLatitude(), pos.getLongitude());
		
		Logger.getLogger("BP").log(Level.INFO,
				"CA MARCHE : " + zones.size());
	}

	/*
	@Override
	public Collection<Document> receiveDocumentOrder(User user) {
		// call authent
		// call service from indexation
		return null;
	}*/

}
