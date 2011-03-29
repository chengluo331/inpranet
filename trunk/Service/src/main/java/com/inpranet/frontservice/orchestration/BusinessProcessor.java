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
import com.inpranet.core.ws.indexation.RequestEngineSEI;
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

	/** WS Habit */
	private IHabitService habitService;

	/** WS Zone */
	private IZoneService zoneService;
	
	/** WS Indexation */
	private RequestEngineSEI indexationService;
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
	new String[] { "inpranet-service.xml" });

	public BusinessProcessor() {
		// TODO: changer ce systeme avec autowire=byName dans les beans
		// chargement des services web
		
		zoneService = (IZoneService) context.getBean("zoneService");
		habitService = (IHabitService) context.getBean("habitService");
		indexationService = (RequestEngineSEI) context.getBean("indexationService");
	}

	/**
	 * Processus metier de reception d'une donnee geospatiale
	 * 
	 * @param user
	 * @param pos
	 */
	public void receiveRawPositions(final User user, final GeoPos pos) {

		log.info("Business Process: handling new geoposition. Date: " + pos.getTime().toGregorianCalendar().getTime().toString());

		log.info("Step1: call Zone module to get list of zones from a geoposition");
		// recuperation des zones qui englobent le point GPS
		List<Zone> zones = zoneService.getZoneListFromPos(pos.getLongitude(),
				pos.getLatitude());
		log.info("Zone module returned " + zones.size() + " zones");

		log.info("Step2: call Habit module to store geoposition and zones");
		// passe la donnee spatiale brute au module habit
		habitService.stockData(user, pos, zones);
		log.info("Habit module seems to have stored the data...");
	}

	/**
	 * Processus metier d'authentification de l'utilisateur via un token
	 * (session)
	 * 
	 * @param pos
	 */
	public User authenticateUser(String token) throws ServiceException {
		// TODO: a connecter au module Profil

		// ====== code temporaire==================
		//  creation d'un user avec comme id son token
		User user = new User();
		user.setIdUser(Integer.parseInt(token));
		user.setPlanningHorizon(60); // 60 minutes;
		List<Interest> interestList = new ArrayList<Interest>();
		Interest i = new Interest();
		i.setIdInterest(1); // 1st id 
		interestList.add(i);
		user.setInterests(interestList);
		// ====== fin code temporaire==================
		
		return user;
	}

	@Override
	public Collection<Document> receiveDocumentOrder(User user) {
		log.info("Business Process: get documents connected to predicted zones");
		
		log.info("Step1: call Zone module to get list of zones from a geoposition");
		// TODO: changer interface de ce service : il n'a plus que user
		List<Zone> zones = habitService.deduceZone(user, 0, null);
		log.info("Zone module returned " + zones.size() + " zones " + zones.get(0).getIdZone());

		log.info("Step2: call Indexation module to get best documents from predicted zones");
		List<Document> documents = indexationService.launchRequest(user, zones);
		log.info("Indexation module returned " + documents.size() + "documents");
		
		return documents;
	}

	public void setHabitService(IHabitService habitService) {
		this.habitService = habitService;
	}

	public void setZoneService(IZoneService zoneService) {
		this.zoneService = zoneService;
	}

}
