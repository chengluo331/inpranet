package com.inpranet.core.model;

import java.util.Date;

public class Event {

	private long idEvent;
	
	private User user;
	
	private Zone zone;
	
	private Date timeIn;
	
	private Date timeOut;

	public Event(User user, Zone zone, Date timeIn, Date timeOut) {
		super();
		this.user = user;
		this.zone = zone;
		this.timeIn = timeIn;
		this.timeOut = timeOut;
	}

	public long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(long idEvent) {
		this.idEvent = idEvent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public Date getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}

	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}
	
	
}
