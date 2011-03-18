package com.inpranet.habit.model;

import java.sql.Timestamp;

public class Habit {
	
	private int mUserId;
	private Timestamp mDateTime;
	private double mLongtitude;
	private double mLatitude;
	
	public Habit(int userId, Timestamp dateTime, double longtitude, double latitude) {
		this.mUserId = userId;
		this.mDateTime = dateTime;
		this.mLongtitude = longtitude;
		this.mLatitude = latitude;
	}
	
	public int getUserId() {
		return mUserId;
	}
	
	public void setUserId(int userId) {
		this.mUserId = userId;
	}
	
	public Timestamp getDateTime() {
		return mDateTime;
	}
	
	public void setDateTime(Timestamp dateTime) {
		this.mDateTime = dateTime;
	}
	
	public double getLongitude() {
		return mLongtitude;
	}
	
	public void setLongitude(int longitude) {
		this.mLongtitude = longitude;
	}
	
	public double getLatitude() {
		return mLatitude;
	}
	
	public void setLatitude(int latitude) {
		this.mLatitude = latitude;
	}
	
}
