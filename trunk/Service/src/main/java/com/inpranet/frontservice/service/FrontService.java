package com.inpranet.frontservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	private IBusinessProcessor bp = new BusinessProcessor();

	
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
			}
		} 
	}

	/**
	 * See interface {IFrontService}
	 */
	public Collection<Document> getDocumentList(String id) {

		log.info("Received the request to give back a list of documents");
		
		Collection<Document> c = new ArrayList<Document>();
		/*
		Document doc1 = new Document(10,"Server test1",true, "acceuil","http://www.test.com",new Date(),new Date(),123,123,"this is a server test");
		Document doc2 = new Document(11,"Server test2",true, "sport","http://www.test.com",new Date(),new Date(),123,123,"this is a server test");
		c.add(doc1);
		c.add(doc2);
		*/
		return c;
	}


	@GET
	@Path("/json")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public GeoPos testJSON() {

		GeoPos pos = new GeoPos();
		return pos;
	}

}
