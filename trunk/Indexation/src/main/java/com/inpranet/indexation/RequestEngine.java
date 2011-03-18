package com.inpranet.indexation;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.inpranet.indexation.model.Document;
import com.inpranet.indexation.service.DocumentManager;

/**
 * Moteur de requetes de documents
 * @author Stephane
 */
public class RequestEngine {
	/**
	 * Utilisation des services metiers lies aux objets Documents
	 */
	private static DocumentManager documentManager = new DocumentManager();
	
	/**
	 * Lance une requete pour recuperer des documents
	 */
	public static void LaunchRequest() {
		// Recuperation des resultats de la requete
		List<Document> documents;
		
		// Lance la requete
		documents = documentManager.getDocumentByDate(new Date());
		
		// Interpretation des resultats
		Document document;
		ListIterator<Document> i = documents.listIterator();
		while (i.hasNext()) {
			document = i.next();
			System.out.println("RequestEngine : Document trouve : " + document.getTitle() + ", " + document.getData());
		}
	}
}
