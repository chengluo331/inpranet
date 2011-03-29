package com.inpranet.mobile;

public class DocumentInfo {
	private static final int FIRST_LINE_SIZE = 50;
	private long mID;
	private String mTitle;
	private boolean mEventImportance;
	private String mCategory;
	private String mFirstLine;

	public DocumentInfo(long id, String title, boolean eventImportance,
			String category, String firstLine) {
		mID = id;
		mTitle = title;
		mEventImportance = eventImportance;
		mCategory = category;
		mFirstLine = firstLine;
	}

	public long getDocID() {
		return mID;
	}

	public String getDocTitle() {
		return mTitle;
	}

	public boolean isImportant() {
		return mEventImportance;
	}

	public String getDocCategory() {
		return mCategory;
	}

	public String getDocFirstLine() {
		return mFirstLine;
	}

	/**
	 * recuperer les première FIRST_LINE_SIZE caractères d'un document
	 * 
	 * @param string
	 * @return
	 */
	public static String retrevieFL(String data) {
		if (data.length() <= FIRST_LINE_SIZE) {
			return data + "...";
		} else {
			return data.substring(0, FIRST_LINE_SIZE - 1) + "...";
		}
	}

}
