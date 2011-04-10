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
	 * @param user L'utilisateur pour lequel on effectue la requete (filtrage avec les preferences sur les categories)
	 * @param zone La liste des zones sur lequelles faire la requete (cette liste est fournie par Habitude)
	 * @return La liste des documents correspondant aux parametres de la recherche
	 */
	public List<Document> LaunchRequest(User user, List<Zone> zone);
}
