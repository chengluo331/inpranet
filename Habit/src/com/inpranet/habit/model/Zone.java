package com.inpranet.habit.model;

public class Zone {

	private long idZone;
	
	private Interest zoneInterest;

	public Zone(Interest zoneInterest) {
		super();
		this.zoneInterest = zoneInterest;
	}

	public long getIdZone() {
		return idZone;
	}

	public void setIdZone(long idZone) {
		this.idZone = idZone;
	}

	public Interest getZoneInterest() {
		return zoneInterest;
	}

	public void setZoneInterest(Interest zoneInterest) {
		this.zoneInterest = zoneInterest;
	}
	
	
}
