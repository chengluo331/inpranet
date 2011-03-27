package com.inpranet.habit.dao;

import java.util.Date;

import com.inpranet.habit.model.WeeklyHabit;

/**
 * Inteface DAO pour la gestion de l'acces au donnes relatives aux weeklyHabit
 * @author yiquan, wenshao
 *
 */
public interface IWeeklyHabitDAO {
	
	/** Le nom de fonction pour requeter la zone la plus probable d'apres les habitudes */
	public static final String FUNCTION_GET_HABIT_NAME = "getweeklyhabit";
	
	/** Le nom du schéma ou est stockée la fonction */
	public static final String SCHEMA_NAME = "habit";
	
	/**
	 * créer une habitude: incrémentation dans la cube
	 * la fonction fait appel à la procédure stockée insertweeklyhabit.sql
	 * @param weeklyHabit
	 */
	public void createWeeklyHabit(WeeklyHabit weeklyHabit);
	
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
	 * @return
	 */
	public int DeduceIdZoneByInterest(int userId, int interestId, Date time);
}
