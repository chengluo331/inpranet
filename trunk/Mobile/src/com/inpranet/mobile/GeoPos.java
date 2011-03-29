package com.inpranet.mobile;

import java.util.Date;

public class GeoPos {

	private double longitude;

	private double latitude;
	
	private Date time;

	public GeoPos() {

	}

	public GeoPos(double longitude, double latitude, Date time) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
	}
	

	public double getLongitude() {
		return longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public Date getDate() {
		return time;
	}
}
