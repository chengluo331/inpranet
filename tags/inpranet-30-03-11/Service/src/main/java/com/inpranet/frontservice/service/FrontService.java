package com.inpranet.frontservice.service;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import com.inpranet.core.model.Document;
import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.User;
import com.inpranet.frontservice.orchestration.BusinessProcessor;
import com.inpranet.frontservice.orchestration.IBusinessProcessor;
import com.inpranet.frontservice.orchestration.ServiceException;
import com.inpranet.frontservice.orchestration.ServiceException.ErrorCode;

/**
 * FrontService expose des web services type RestFul
 * Il sert de "dispatcher" des differentes requetes et utilise un IBusinessProcessor pour orchestrer 
 * l'ensemble des services sous-jacents necessaires pour repondre a la requete client
 * Voir {IFrontService} pour plus de details
 * @author inpranet team
 *
 */
public class FrontService implements IFrontService {
	
	/** Logger */
	static Logger log = Logger.getLogger(FrontService.class.getName());

	/**
	 * bp est le gestionnaire de processus metier
	 */
	private IBusinessProcessor bp;
	
	public FrontService() {
		bp = new BusinessProcessor();
	}

	
	/**
	 * See interface {IFrontService}
	 */
	public void postGeoPos(String token, GeoPos pos) {
		log.info("Received the request to store a geoposition");
		try {
			// recherche l'utilisateur lié au token
			User user = bp.authenticateUser(token);
			
			// lance le processus de reception d'une donne geospatiale
			bp.receiveRawPositions(user, pos);
			
		} catch(ServiceException e) {
			if (e.getErrorCode() == ErrorCode.BAD_TOKEN) {
				// TODO: renvoyer une erreur a l'utilisateur
				e.printStackTrace();
			}
		} 
	}

	/**
	 * See interface {IFrontService}
	 */
	public Collection<Document> getDocumentList(String token) {

		log.info("Received the request to give back a list of documents");
		
		// recherche l'utilisateur lié au token
		User user;
		try {
			user = bp.authenticateUser(token);
		} catch (ServiceException e) {
			// TODO: renvoyer une erreur a l'utilisateur
			e.printStackTrace();
			return null;
		}
		
		// lance le processus de recuperation de documnts adapte a l'utilisateur
		Collection<Document> documents  = bp.receiveDocumentOrder(user);

		return documents;
	}
	
	public void testDate(Date date) {
		log.info(date.toString());
	}
}
