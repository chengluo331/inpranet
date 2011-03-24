package com.inpranet.habit.model;

import java.util.Date;

/** 
 * Represente un objet Position persistant
 * @author Yiquan
 *
 */
public class Position {
	/** reference l'id du user */
	private int userId;
	
	/** la longitude */
	private double longitude;
	
	/** la latitude */
	private double latitude;
	
	/** l'heure et date*/
	private Date time;

	public Position() {
		
	}
	
	public Position(int userId, double longitude, double latitude, Date time) {
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
