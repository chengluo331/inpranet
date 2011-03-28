package com.inpranet.indexation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.jws.WebService;

import com.inpranet.core.model.Document;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;
import com.inpranet.indexation.service.DocumentManager;

/**
 * Moteur de requetes de documents
 * @author Stephane
 */
@WebService(endpointInterface = "com.inpranet.indexation.RequestEngineSEI")
public class RequestEngine implements RequestEngineSEI {
	/**
	 * Utilisation des services metiers lies aux objets Documents
	 */
	private static DocumentManager documentManager = new DocumentManager();
	
	/**
	 * Lance une requete pour recuperer des documents
	 */
	public List<Document> LaunchRequest(User user, List<Zone> zone) {
		// TODO : Changer le nom de zone en zonesList
		
		// Variable chargee de la recuperation des resultats de la requete
		List<Document> documents = new ArrayList<Document>();
		
		// Recuperation des informations propres a l'utilisateur
		// Date planificationHorizon = user.getPlanificationHorizon();
		// List<Interest> userInterests = user.getInterests();
		
		// Lance la requete à partir de la date d'aujourd'hui et en selectionnant les zones passees en parametre
		// documents = documentManager.getDocumentByDate(new Date());
		documents = documentManager.getDocumentByDateZones(new Date(), zone);
		
		// Filtrage des resultats selon la categorie
		
		// Interpretation des resultats
		Document document;
		ListIterator<Document> i = documents.listIterator();
		while (i.hasNext()) {
			document = i.next();
			System.out.println("RequestEngine : Document trouve : " + document.getTitle() + ", " + document.getData());
		}
		
		return documents;
	}
}
