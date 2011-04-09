package com.inpranet.habit.dao;

import java.sql.SQLException;
import java.util.Date;

import com.inpranet.habit.model.WeeklyHabit;

/**
 * Inteface DAO pour la gestion de l'acces au donnes relatives aux weeklyHabit
 * @author yiquan, wenshao
 *
 */
public interface IWeeklyHabitDAO {
	
	/** Le nom de la fonction pour ajouter une habitude */
	public static final String FUNCTION_INSERT_HABIT = "insertweeklyhabit";
	
	/** Le nom de fonction pour requ�ter la zone la plus probable d'apr�s les habitudes */
	public static final String FUNCTION_GET_HABIT_NAME = "gethabit";
	
	/** Le nom du sch�ma o� est stock�e la fonction */
	public static final String SCHEMA_NAME = "habit";
	
	/**
	 * Cr�er une habitude: incr�mentation dans la cube
	 * La fonction fait appel � la proc�dure stock�e insertweeklyhabit.sql
	 * @param weeklyHabit Une habitude
	 * @throws SQLException Exception de persistence
	 */
	public void createWeeklyHabit(WeeklyHabit weeklyHabit) throws SQLException;
	
	/**
	 * R�cup�rer l'identifiant de l'interval du temps dans lequel l'heure se trouve
	 * @param time 
	 * @return l'identifiant de l'interval
	 */
	public int GetIdInterval(Date time);
	
	/**
	 * D�duire la zone la plus probable par centre int�r�t
	 * @param userId id de l'utilisateur
	 * @param interestId id du centre d'int�r�t
	 * @param time l'heure
	 * @return l'id zone la plus probable
	 * @throws NullPointerException Aucune zone est trouv�e
	 * @throws SQLException Erreur de persistence
	 */
	public int DeduceIdZoneByInterest(int userId, int interestId, Date time) throws SQLException, NullPointerException;
}
