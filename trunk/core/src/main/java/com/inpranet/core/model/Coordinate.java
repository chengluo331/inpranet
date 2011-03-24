package com.inpranet.core.model;


public class Coordinate {

	private double longtitude;
	
	private double latitude;

	public Coordinate(double longtitude, double latitude) {
		super();
		this.longtitude = longtitude;
		this.latitude = latitude;
	}
	
	public Coordinate() {
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
	
}
