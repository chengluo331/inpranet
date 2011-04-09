package com.inpranet.habit.model;

import java.util.Date;


public class WeeklyHabit {
	
	/** L'identifiant de l'utilisateur */
	private int mUserId;
	
	/** L'identifiant d'une tranche horaire de la semaine */
	private int mTimeOfWeekId;  
	
	/** L'identifiant de la zone */
	private int mZoneId;
	
	/** Le nombre d'occurrence de la présence de l'utilisateur */
	private int mNbrOccurrence;
	
	/** Flag qui signale l'état de la mise à jour
	 *  0 si l'incrémentation de nbOccurence est autorisée
	 *  1 si l'incrémentation est déjà faite une fois pour cette tranche horaire cette semaine, donc plus autorisée
	 */
	private int mFlag;
	
	/**
	 * Constructeur vide
	 */
	public WeeklyHabit(){
		
	}

	/**
	 * Constructeur
	 * @param mUserId L'identifiant de l'utilisateur
	 * @param mDateTime L'identifiant d'une tranche horaire 
	 * @param mZoneId L'identifiant d'une zone
	 * @param mNbrOccurrence Le nombre d'occurence
	 * @param mFlag Le flag
	 */
	public WeeklyHabit(int mUserId, int mDateTime, int mZoneId,
			int mNbrOccurrence, int mFlag) {
		this.mUserId = mUserId;
		this.mTimeOfWeekId = mDateTime;
		this.mZoneId = mZoneId;
		this.mNbrOccurrence = mNbrOccurrence;
		this.mFlag = mFlag;
	}


	public int getmUserId() {
		return mUserId;
	}


	public void setmUserId(int mUserId) {
		this.mUserId = mUserId;
	}


	public int getmDateTime() {
		return mTimeOfWeekId;
	}


	public void setmDateTime(int mDateTime) {
		this.mTimeOfWeekId = mDateTime;
	}


	public int getmZoneId() {
		return mZoneId;
	}


	public void setmZoneId(int mZoneId) {
		this.mZoneId = mZoneId;
	}


	public int getmNbrOccurrence() {
		return mNbrOccurrence;
	}


	public void setmNbrOccurrence(int mNbrOccurrence) {
		this.mNbrOccurrence = mNbrOccurrence;
	}


	public int getmFlag() {
		return mFlag;
	}


	public void setmFlag(int mFlag) {
		this.mFlag = mFlag;
	}
	

	
}
