package com.inpranet.indexation.service;

import java.util.Date;
import java.util.List;

import com.inpranet.indexation.dao.DocumentDAO;
import com.inpranet.indexation.model.Document;

/**
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
	 */
	public List<Document> getDocumentByDate(Date date) {
		return documentDao.findDocumentByDate(date);
	}
}
