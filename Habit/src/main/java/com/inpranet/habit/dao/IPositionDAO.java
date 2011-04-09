package com.inpranet.habit.dao;

import java.sql.SQLException;

import com.inpranet.habit.model.Position;

/**
 * Inteface DAO pour la gestion de l'acces au donnes relatives aux documents
 * @author Wenshao, Yiquan
 */
public interface IPositionDAO {
	
	/** Le nom de la table qui stock les données géo-position brutes */
	public final String TABLE_POSITION = "habit.position";
	
	/**
	 * Créer une position dans la base
	 * @param position La position a creer dans la base
	 * @throws SQLException Exception de persistence
	 */
	public void createPosition(Position position) throws SQLException;
	
	

}
