package com.inpranet.indexation.dao;

import java.util.Date;
import java.util.List;

import com.inpranet.core.model.*;

/**
 * Inteface DAO pour la gestion de l'acces au donnes relatives aux documents
 * @author Stephane
 */
public interface IDocumentDAO {
	/**
	 * Cree un document dans la base
	 * @param document Le document a creer dans la base
	 */
	public void createDocument(Document document);
	
	/**
	 * Supprime un document dans la base
	 * @param document Le document a supprimer
	 */
	public void deleteDocument(Document document);
	
	/**
	 * Recherche des documents qui couvrent une certaine date
	 * @param date La date a couvrir
	 */
	public List<Document> findDocumentByDate(Date date);
	
	/**
	 * Recherche des documents qui correspondent a certaines categories
	 */
	public List<Document> findDocumentByCategories(List<Category> categoriesList);
	
	/**
	 * Recherche des documents qui correspondent a certaines zones
	 */
	public List<Document> findDocumentByZones(List<Zone> zonesList);
}
