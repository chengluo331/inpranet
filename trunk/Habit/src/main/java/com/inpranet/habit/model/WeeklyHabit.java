package com.inpranet.habit.model;

import java.util.Date;

import com.inpranet.zone.model.Zone;

public class WeeklyHabit {
	private int mUserId;
	private Date mDateTime;  //verifier si c'est java.sql.date ou java.util.date
	private Zone mZoneId;
	private String mInterest;
	private int mNbrOccurrence;
	
	public WeeklyHabit(){
		
	}
	
	public WeeklyHabit(int userId, Date dateTime, Zone zoneId, String interest, int nbrOccurrence) {
		this.mUserId = userId;
		this.mDateTime = dateTime;
		this.mZoneId = zoneId;
		this.mInterest = interest;
		this.mNbrOccurrence = nbrOccurrence;
	}
	
	public int getUserId() {
		return mUserId;
	}
	
	public void setUserId(int userId) {
		this.mUserId = userId;
	}
	
	public Date getDateTime() {
		return mDateTime;
	}
	
	public void setDateTime(Date dateTime) {
		this.mDateTime = dateTime;
	}
		
	public Zone getZoneId() {
		return mZoneId;
	}
	
	public void setZoneId(int userId, Date dateTime) {
		this.mUserId = userId;
	}
	
	public String getInterest() {
		return mInterest;
	}
	
	public void setInterest(String interest) {
		this.mInterest = interest;
	}

	public int getNbrOccurrence() {
		return mNbrOccurrence;
	}
	
	public void setNbrOccurrence(int nbrOccurrence) {
		this.mNbrOccurrence = nbrOccurrence;
	}
	
	
}
