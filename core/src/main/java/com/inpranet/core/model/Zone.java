package com.inpranet.core.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "zone")
public class Zone {

	@XmlElement
	private long idZone;
	
	@XmlElement
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
