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
	
	/** Le nom du sch�ma ou est stock�e la fonction */
	public static final String SCHEMA_NAME = "habit";
	
	/**
	 * cr�er une habitude: incr�mentation dans la cube
	 * la fonction fait appel � la proc�dure stock�e insertweeklyhabit.sql
	 * @param weeklyHabit
	 */
	public void createWeeklyHabit(WeeklyHabit weeklyHabit);
	
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
	 * @return
	 */
	public int DeduceIdZoneByInterest(int userId, int interestId, Date time);
}
