package com.inpranet.profil.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.inpranet.profil.model.HabitType;


/**
 * Cette interface definit les webservices offerts par le module profil
 * @author Yiquan
 *
 */

@WebService
public interface IProfilService {
	@WebMethod
	public String Authentification(
			@WebParam(name="login") String login,
			@WebParam(name="password") String password);
	
	@WebMethod
	public void SetHabitType(
			@WebParam(name="habitType") HabitType habitType);
}
