package com.inpranet.indexation;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.inpranet.indexation.model.Document;
import com.inpranet.indexation.service.DocumentManager;

import javax.jws.WebService;

/**
 * Moteur de requetes de documents
 * @author Stephane
 */
@WebService(endpointInterface = "com.inpranet.indexation.RequestEngineSEI")
public class RequestEngine implements RequestEngineSEI {
	/**
	 * Parametrage de la fin de ligne
	 */
	private final String EOL = "\n";
	
	/**
	 * Utilisation des services metiers lies aux objets Documents
	 */
	private static DocumentManager documentManager = new DocumentManager();
	
	/**
	 * Lance une requete pour recuperer des documents
	 */
	public StringBuffer LaunchRequest() {
		// Recuperation des resultats de la requete
		List<Document> documents;
		StringBuffer awnser = new StringBuffer();
		
		// Lance la requete
		documents = documentManager.getDocumentByDate(new Date());
		
		// Interpretation des resultats
		Document document;
		ListIterator<Document> i = documents.listIterator();
		while (i.hasNext()) {
			document = i.next();
			System.out.println("RequestEngine : Document trouve : " + document.getTitle() + ", " + document.getData());
			awnser.append("Document trouve : " + document.getTitle() + ", " + document.getData() + EOL);
		}
		
		System.out.println("RequestEngine : " + awnser);
		return awnser;
	}
}
