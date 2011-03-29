package com.inpranet.mobile;


import java.text.SimpleDateFormat;
import java.util.Date;


public class LocationInfo {
	private long mSessionID;
	private GeoPos mGeoPos;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
	"yyyy-MM-dd'T'HH:mm:ss");
	
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

	public String getTimeInCalendarFormat() {
		return dateFormat.format(mGeoPos.getDate());
	}
}