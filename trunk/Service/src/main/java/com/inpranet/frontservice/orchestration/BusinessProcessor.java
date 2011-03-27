package com.inpranet.frontservice.orchestration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.core.model.Document;
import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Interest;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;
import com.inpranet.core.ws.habit.IHabitService;
import com.inpranet.core.ws.zone.IZoneService;

/**
 * Voir {IBusinessProcessor}
 * 
 * @author inpranet team
 * 
 */
public class BusinessProcessor implements IBusinessProcessor {

	/** Logger */
	private static Logger log = Logger.getLogger(BusinessProcessor.class
			.getName());

	private IHabitService habitService;

	private IZoneService zoneService;
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
	new String[] { "inpranet-service.xml" });

	public BusinessProcessor() {
		// TODO: changer ce systeme avec autowire=byName dans les beans
		// chargement des services web
		
		zoneService = (IZoneService) context.getBean("zoneService");
		habitService = (IHabitService) context.getBean("habitService");
	}

	/**
	 * Processus metier de reception d'une donnee geospatiale
	 * 
	 * @param user
	 * @param pos
	 */
	public void receiveRawPositions(final User user, final GeoPos pos) {

		log.info("Business Process: handling new geoposition");

		log.info("Step1: call Zone module to get list of zones from a geoposition");
		List<Zone> zones = zoneService.getZoneListFromPos(pos.getLongitude(),
				pos.getLatitude());
		log.info("Zone module returned " + zones.size() + " zones");

		log.info("Step1: call Habit module to store geoposition and zones");
		habitService.stockData(user, pos, zones);
	}

	/**
	 * Processus metuer d'authentification de l'utilisateur via un token
	 * (session)
	 * 
	 * @param pos
	 */
	public User authenticateUser(String token) throws ServiceException {
		// TODO: a connecter au module Profil

		// code temporaire : creation d'un user avec comme id son token
		return new User(Integer.parseInt(token), null, null);
	}

	@Override
	public Collection<Document> receiveDocumentOrder(User user) {

		// TODO: changer ces par les bonnes
		// pas deja connu dans User ???
		int planningHorizon = 45;
		List<Interest> interestList = new ArrayList<Interest>();
		Interest i = new Interest();
		i.setName("Commercial");
		interestList.add(i);

		List<Zone> zones = habitService.deduceZone(user, planningHorizon,
				interestList);

		// TODO: appeller service d'indexation

		return null;
	}

	public void setHabitService(IHabitService habitService) {
		this.habitService = habitService;
	}

	public void setZoneService(IZoneService zoneService) {
		this.zoneService = zoneService;
	}

}
