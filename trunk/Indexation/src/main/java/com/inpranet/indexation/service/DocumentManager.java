package com.inpranet.indexation.service;

import java.util.Date;
import java.util.List;

import com.inpranet.core.model.Category;
import com.inpranet.core.model.Document;
import com.inpranet.core.model.Zone;
import com.inpranet.indexation.dao.DocumentDAO;

/**
 * Implementation des services objet metier relatives aux documents
 * @author Stephane
 */
public class DocumentManager implements IDocumentManager {
	/**
	 * DAO qui gere l'acces aux donnes relatives aux Documents
	 */
	private DocumentDAO documentDao = new DocumentDAO();
	
	/**
	 * Enregistre un document dans la base
	 * @param document Le document a enregistrer
	 */
	public void saveDocument(Document document) {
		documentDao.createDocument(document);
	}
	
	/**
	 * Recupere les documents qui couvrent une certaine date
	 * @param date La date a couvrir
	 * @return La liste des documents correspondant aux parametres de la recherche
	 */
	public List<Document> getDocumentByDate(Date date) {
		return documentDao.findDocumentByDate(date);
	}
	
	/**
	 * Recupere les documents qui correspondent a certaines categories
	 * @param categoriesList Les categories a couvrir
	 * @return La liste des documents correspondant aux parametres de la recherche
	 */
	public List<Document> getDocumentByCategories(List<Category> categoriesList) {
		return documentDao.findDocumentByCategories(categoriesList);
	}
	
	/**
	 * Recupere les documents qui correspondent a certaines zones
	 * @param zonesList Les zones a couvrir
	 * @return La liste des documents correspondant aux parametres de la recherche
	 */
	public List<Document> getDocumentByZones(List<Zone> zonesList) {
		return documentDao.findDocumentByZones(zonesList);
	}
	
	/**
	 * Recupere les documents qui correspondent a une certaine date et a certaines zones
	 * @param date La date sur laquelle effectuer la recherche
	 * @param zonesList Les zones a couvrir
	 * @return La liste des documents correspondant aux parametres de la recherche
	 */
	public List<Document> getDocumentByDateZones(Date date, List<Zone> zonesList) {
		return documentDao.findDocumentByDateZones(date, zonesList);
	}
}
