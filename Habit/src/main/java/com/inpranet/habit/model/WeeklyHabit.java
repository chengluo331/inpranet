package com.inpranet.habit.model;

import java.util.Date;


public class WeeklyHabit {
	private int mUserId;
	private int mTimeOfWeekId;  //verifier si c'est java.sql.date ou java.util.date
	private int mZoneId;
	private int mNbrOccurrence;
	private int mFlag;
	
	
	public WeeklyHabit(){
		
	}


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
