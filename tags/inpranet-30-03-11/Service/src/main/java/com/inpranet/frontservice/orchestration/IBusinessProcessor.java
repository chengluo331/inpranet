package com.inpranet.frontservice.orchestration;

import java.util.Collection;

import com.inpranet.core.model.Document;
import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.User;


/**
 * Interface à suivre pour implementer un BusinessProcessor dedie a la gestion des processus metier : orchestration
 * Chaque methode a implementer est un Processus Metier 
 * @author inpranet team
 *
 */
public interface IBusinessProcessor {

	/**
	 * Reception d'une donnee geospatiale liee a un utilisateur
	 * @param user utilisateur a qui a envoye la donee
	 * @param pos position geospatiale
	 */
	public void receiveRawPositions(final User user, final GeoPos pos) throws ServiceException;
	
	/**
	 * Verifie l'identite d'une connection 
	 * @param token jeton d'identification
	 * @return l'objet User correspondant a ce jeton de session
	 * @throws ServiceException
	 */
	public User authenticateUser(final String token) throws ServiceException;
	
	
	public Collection<Document> receiveDocumentOrder(final User user);
	
	// TODO: getToken(login, mdp)
	// TODO: creationprofil(login, mdp)
}
