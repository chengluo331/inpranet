package com.inpranet.frontservice.model;

public class GeoPos {

	private double longitude;
	private double latitude;
	private String time;
	
	public GeoPos() {
		
	}
	
	public GeoPos(double longitude, double latitude, String time) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
