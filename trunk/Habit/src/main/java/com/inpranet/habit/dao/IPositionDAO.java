package com.inpranet.habit.dao;

import com.inpranet.habit.model.Position;

/**
 * Inteface DAO pour la gestion de l'acces au donnes relatives aux documents
 * @author Wenshao, Yiquan
 */
public interface IPositionDAO {
	
	/** nom de la table */
	public final String TABLE_POSITION = "habit.position";
	
	/**
	 * Cree une position dans la base
	 * @param position La position a creer dans la base
	 */
	public void createPosition(Position position);
	
	

}
