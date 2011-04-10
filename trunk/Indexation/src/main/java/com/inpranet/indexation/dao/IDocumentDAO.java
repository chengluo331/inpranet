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
	 * @return La liste des documents correspondant aux parametres de la recherche
	 */
	public List<Document> findDocumentByDate(Date date);
	
	/**
	 * Recherche des documents qui correspondent a certaines categories
	 * @param categoriesList La liste des categories sur laquelle faire la recherche
	 * @return La liste des documents correspondant aux parametres de la recherche
	 */
	public List<Document> findDocumentByCategories(List<Category> categoriesList);
	
	/**
	 * Recherche des documents qui correspondent a certaines zones
	 * @param zonesList La liste des zones sur laquelle faire la recherche
	 * @return La liste des documents correspondant aux parametres de la recherche
	 */
	public List<Document> findDocumentByZones(List<Zone> zonesList);
	
	/**
	 * Recherche des documents qui correspondent a une certaine date et a certaines zones
	 * @param date La date sur laquelle faire la recherche
	 * @param zonesList La liste des zones sur laquelle faire la recherche
	 * @return La liste des documents correspondant aux parametres de la recherche
	 */
	public List<Document> findDocumentByDateZones(Date date, List<Zone> zonesList);
}
