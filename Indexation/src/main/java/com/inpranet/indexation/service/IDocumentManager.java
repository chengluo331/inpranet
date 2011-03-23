package com.inpranet.indexation.service;

import java.util.Date;
import java.util.List;

import com.inpranet.indexation.model.Document;

/**
 * Interface offrant des services metiers pour la gestion des objets Documents
 * @author Stephane
 */
public interface IDocumentManager {
	/**
	 * Enregistre un document dans la base
	 * @param document Le document a enregistrer
	 */
	public void saveDocument(Document document);
	
	/**
	 * Recupere les documents qui couvrent une certaine date
	 * @param date La date a couvrir
	 */
	public List<Document> getDocumentByDate(Date date);
}
