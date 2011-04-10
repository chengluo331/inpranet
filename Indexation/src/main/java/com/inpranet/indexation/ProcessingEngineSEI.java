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
	 * @param inputDocumentPath Le chemin d'acces au document a traiter
	 * @return Le resultat de la recherche de dates et de lieux sous la forme "Dates trouvees, Lieux trouves"
	 */
	public String StartProcessing(String inputDocumentPath);
}
