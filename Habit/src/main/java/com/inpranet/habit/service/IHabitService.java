package com.inpranet.habit.service;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Interest;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;

/**
 * Cette interface definit les webservices offerts par le module habit
 * @author Yiquan
 *
 */
@WebService
public interface IHabitService {
	/**
	 * Sauvegarder un position
	 * @param user l'objet utilisateur
	 * @param geoPos l'objet geoPos contenant la coordonnee et la date
	 * @param zones la liste des zones 
	 */
	@WebMethod
	public void StockData(
			@WebParam(name="user") User user,
			@WebParam(name="geoPos") GeoPos geoPos,
			@WebParam(name="zones") Collection<Zone> zones);
	
	/**
	 * Deduire les zones interessantes pour un utilisateur
	 * @param user l'objet utilisateur
	 * @param planningHorizon l'horizon de planification
	 * @param interests les centres d'interets de l'utilisateur
	 * @return Collection<Zone> la liste des zones interessantes 
	 */
	@WebMethod
	public Collection<Zone> DeduceZone(
			@WebParam(name="user") User user, 
			@WebParam(name="planningHorizon") int planningHorizon, 
			@WebParam(name="interests") Collection<Interest> interests);
}
