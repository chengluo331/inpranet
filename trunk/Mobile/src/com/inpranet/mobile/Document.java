package com.inpranet.mobile;

import java.util.Date;

public class Document {
	private long mID;
	private String mTitle;
	private String mZone;
	private boolean mEventImportance; 
	private String mCategory;
	private Date mStartDate;
	private Date mEndDate;
	private String mFirstLine;
	private String mHtmlData;
	
	public Document(long id, String title, String zone, boolean eventImportance, 
			String category, Date startDate, Date endDate, String firstLine, String htmlData){
		mID = id;
		mTitle = title;
		mZone = zone;
		mEventImportance = eventImportance;
		mCategory = category;
		mStartDate = startDate;
		mEndDate = endDate;
		mFirstLine = firstLine;
		mHtmlData = htmlData;
	}
	
	public String getDocTitle(){
		return mTitle;
	}
	
	public String getDocZone(){
		return mZone;
	}
	
	public boolean isImportant(){
		return mEventImportance;
	}
	
	public String getDocCategory(){
		return mCategory;
	}
	
	public Date getDocStartDate(){
		return mStartDate;
	}
	
	public Date getDocEndDate(){
		return mEndDate;
	}
	
	public String getDocFirstLine(){
		return mFirstLine;
	}
	
	public String getDocHtmlData(){
		return mHtmlData;
	}
}
