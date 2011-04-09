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
	
	/** Le nom de fonction pour requêter la zone la plus probable d'après les habitudes */
	public static final String FUNCTION_GET_HABIT_NAME = "gethabit";
	
	/** Le nom du schéma où est stockée la fonction */
	public static final String SCHEMA_NAME = "habit";
	
	/**
	 * Créer une habitude: incrémentation dans la cube
	 * La fonction fait appel à la procédure stockée insertweeklyhabit.sql
	 * @param weeklyHabit Une habitude
	 * @throws SQLException Exception de persistence
	 */
	public void createWeeklyHabit(WeeklyHabit weeklyHabit) throws SQLException;
	
	/**
	 * Récupérer l'identifiant de l'interval du temps dans lequel l'heure se trouve
	 * @param time 
	 * @return l'identifiant de l'interval
	 */
	public int GetIdInterval(Date time);
	
	/**
	 * Déduire la zone la plus probable par centre intérêt
	 * @param userId id de l'utilisateur
	 * @param interestId id du centre d'intérêt
	 * @param time l'heure
	 * @return l'id zone la plus probable
	 * @throws NullPointerException Aucune zone est trouvée
	 * @throws SQLException Erreur de persistence
	 */
	public int DeduceIdZoneByInterest(int userId, int interestId, Date time) throws SQLException, NullPointerException;
}
