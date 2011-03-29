package com.inpranet.indexation.service;

import java.util.Date;
import java.util.List;

import com.inpranet.core.model.Category;
import com.inpranet.core.model.Document;
import com.inpranet.core.model.Zone;

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
	
	/**
	 * Recupere les documents qui correspondent a certaines categories
	 * @param zonesList Les categories a couvrir
	 */
	public List<Document> getDocumentByCategories(List<Category> categoriesList);
		
	/**
	 * Recupere les documents qui correspondent a certaines zones
	 * @param zonesList Les zones a couvrir
	 */
	public List<Document> getDocumentByZones(List<Zone> zonesList);
		
	/**
	 * Recupere les documents qui correspondent a une certaine date et a certaines zones
	 * @param zonesList Les zones a couvrir
	 */
	public List<Document> getDocumentByDateZones(Date date, List<Zone> zonesList);
}
