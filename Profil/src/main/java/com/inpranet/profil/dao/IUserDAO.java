package com.inpranet.profil.dao;

import com.inpranet.core.model.User;

/**
 * Inteface DAO pour la gestion de l'acces au donnes relatives aux users
 * @author Yiquan
 */
public interface IUserDAO {
	/** nom de la table */
	public final String TABLE_USER = "profil.user";
	
	public final String SEQ_USER_ID = "seq_user_id";
	
	/**
	 * Cree un user dans la base
	 * @param user
	 */
	public void createUser(User user);
	
	public User getUser(String login, String password);
}
