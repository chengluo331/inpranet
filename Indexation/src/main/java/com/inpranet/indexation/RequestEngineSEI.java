package com.inpranet.indexation;

import java.util.List;

import javax.jws.WebService;

import com.inpranet.core.model.Document;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;

/**
 * Interface utilisee pour la generation du Web Service a l'aide de CXF
 * @author Stephane
 */
@WebService
public interface RequestEngineSEI {
	/**
	 * Lance le requetage d'un document pour une recherche normale (mode predictif)
	 * @param user L'utilisateur qui a effectue la requete
	 * @param zone Les zones dans lequelles on cherche des documents
	 */
	public List<Document> LaunchRequest(User user, List<Zone> zone);
}
