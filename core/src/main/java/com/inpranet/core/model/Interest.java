package com.inpranet.core.model;

public class Interest {

	private long idInterest;
	
	private String name;
	
	private String description;

	public Interest(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public long getIdInterest() {
		return idInterest;
	}

	public void setIdInterest(long idInterest) {
		this.idInterest = idInterest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
