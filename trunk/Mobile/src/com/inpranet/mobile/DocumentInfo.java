package com.inpranet.mobile;

public class DocumentInfo {
	private long mID;
	private String mTitle;
	private boolean mEventImportance; 
	private String mCategory;
	private String mFirstLine;
	
	public DocumentInfo(long id, String title,boolean eventImportance, 
			String category, String firstLine){
		mID = id;
		mTitle = title;
		mEventImportance = eventImportance;
		mCategory = category;
		mFirstLine = firstLine;
	}
	
	public long getDocID(){
		return mID;
	}
	
	public String getDocTitle(){
		return mTitle;
	}
	
	
	public boolean isImportant(){
		return mEventImportance;
	}
	
	public String getDocCategory(){
		return mCategory;
	}
	
	public String getDocFirstLine(){
		return mFirstLine;
	}
	
}
