package com.inpranet.profil.dao;

import java.util.Date;
import java.util.List;

import com.inpranet.profil.model.HabitType;

/**
 * Inteface DAO pour la gestion de l'acces au donnes relatives aux habitType
 * @author Yiquan
 */
public interface IHabitTypeDAO {
	/** nom de la table */
	public final String TABLE_HABITTYPE = "profil.habitType";
	
	/**
	 * Cree un habitType dans la base
	 * @param habitType
	 */
	public void createHabitType(HabitType habitType);
}
