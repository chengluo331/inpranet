package com.inpranet.indexation;

import javax.jws.WebService;

/**
 * Interface utilisee pour la generation du Web Service a l'aide de CXF
 * @author Stephane
 */
@WebService
public interface ProcessingEngineSEI {
	/**
	 * Lance le traitement d'un document
	 * @param documentPath Le chemin d'acces au document a traiter
	 */
	public String StartProcessing(String inputDocumentPath);
}
