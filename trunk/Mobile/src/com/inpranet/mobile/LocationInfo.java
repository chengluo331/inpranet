package com.inpranet.mobile;

import java.util.Date;

public class LocationInfo {
	private long mUserID;
	private Date mTime;
	private double longitude;
	private double latitude;
	
	public LocationInfo(long id, Date time, double lg, double la){
		mUserID = id;
		mTime = time;
		longitude = lg;
		latitude = la;
	}
	
	public long getUserID(){
		return mUserID;
	}
	
	public Date getTime(){
		return mTime;
	}
	
	public double getLongitude(){
		return longitude;
	}
	
	public double getLatitude(){
		return latitude;
	}
}

