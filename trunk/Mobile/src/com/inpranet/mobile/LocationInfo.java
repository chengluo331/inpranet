package com.inpranet.mobile;


import java.util.Date;


public class LocationInfo {
	private long mSessionID;
	private GeoPos mGeoPos;
	
	public LocationInfo(long id, GeoPos geoPos){
		mSessionID = id;
		mGeoPos = geoPos;
	}
	
	public long getUserID(){
		return mSessionID;
	}
	
	public Date getDate(){
		return mGeoPos.getDate();
	}
	
	public double getLongitude(){
		return mGeoPos.getLongitude();
	}
	
	public double getLatitude(){
		return mGeoPos.getLatitude();
	}
	
	public GeoPos getGeoPos(){
		return mGeoPos;
	}
}