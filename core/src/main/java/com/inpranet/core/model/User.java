package com.inpranet.core.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Classe d'entite utilisateur
 * @author yqzhou
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
    "idUser",
    "login",
    "password"
})
public class User {

	private int idUser;
	
	private String login;
	
	private String password;
	
	private int planningHorizon;
	
	private List<Interest> interests;

	public User() {
	}

	public User(int id, String login, String password) {
		this.idUser = id;
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPlanningHorizon() {
		return planningHorizon;
	}

	public void setPlanningHorizon(int planningHorizon) {
		this.planningHorizon = planningHorizon;
	}

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
	
	
	
}
