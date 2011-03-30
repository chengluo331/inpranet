package com.inpranet.profil.model;

import java.util.Calendar;

/**
 * Represente un objet HabitType persistant
 * @author yiquan
 *
 */
public class HabitType {
	/** le type d'habitude d'un jour de la semaine: quotidien ou hebdomadaire */
	/** public enum Type {
		QUOTIDIEN, HEBDOMADAIRE
	} */
	
	public static final int QUOTIDIEN = 0;
	
	public static final int HEBDOMADAIRE = 1;
	
	/** reference l'id du user */
	private int userId;
	
	/** le type d'habitude de la semaine */
	private int mondayHabit;
	
	private int tuesdayHabit;
	
	private int wednesdayHabit;
	
	private int thursdayHabit;
	
	private int fridayHabit;
	
	private int saturdayHabit;
	
	private int sundayHabit;

	
	public HabitType() {
		
	}


	public HabitType(int userId, int mondayHabit, int tuesdayHabit,
			int wednesdayHabit, int thursdayHabit, int fridayHabit,
			int saturdayHabit, int sundayHabit) {
		super();
		this.userId = userId;
		this.mondayHabit = mondayHabit;
		this.tuesdayHabit = tuesdayHabit;
		this.wednesdayHabit = wednesdayHabit;
		this.thursdayHabit = thursdayHabit;
		this.fridayHabit = fridayHabit;
		this.saturdayHabit = saturdayHabit;
		this.sundayHabit = sundayHabit;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getMondayHabit() {
		return mondayHabit;
	}


	public void setMondayHabit(int mondayHabit) {
		this.mondayHabit = mondayHabit;
	}


	public int getTuesdayHabit() {
		return tuesdayHabit;
	}


	public void setTuesdayHabit(int tuesdayHabit) {
		this.tuesdayHabit = tuesdayHabit;
	}


	public int getWednesdayHabit() {
		return wednesdayHabit;
	}


	public void setWednesdayHabit(int wednesdayHabit) {
		this.wednesdayHabit = wednesdayHabit;
	}


	public int getThursdayHabit() {
		return thursdayHabit;
	}


	public void setThursdayHabit(int thursdayHabit) {
		this.thursdayHabit = thursdayHabit;
	}


	public int getFridayHabit() {
		return fridayHabit;
	}


	public void setFridayHabit(int fridayHabit) {
		this.fridayHabit = fridayHabit;
	}


	public int getSaturdayHabit() {
		return saturdayHabit;
	}


	public void setSaturdayHabit(int saturdayHabit) {
		this.saturdayHabit = saturdayHabit;
	}


	public int getSundayHabit() {
		return sundayHabit;
	}


	public void setSundayHabit(int sundayHabit) {
		this.sundayHabit = sundayHabit;
	}


	public static int getQuotidien() {
		return QUOTIDIEN;
	}


	public static int getHebdomadaire() {
		return HEBDOMADAIRE;
	}
}
	