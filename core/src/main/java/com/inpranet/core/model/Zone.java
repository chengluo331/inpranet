package com.inpranet.core.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "zone", namespace =
    "http://www.inpranet.com/zone")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Zone {

	
	private long idZone;
	
	
	private Interest zoneInterest;

	public Zone(Interest zoneInterest) {
		super();
		this.zoneInterest = zoneInterest;
	}
	
	public Zone() {
		
	}

	@XmlElement
	public long getIdZone() {
		return idZone;
	}

	public void setIdZone(long idZone) {
		this.idZone = idZone;
	}

	@XmlElement
	public Interest getZoneInterest() {
		return zoneInterest;
	}

	public void setZoneInterest(Interest zoneInterest) {
		this.zoneInterest = zoneInterest;
	}
	
	
}
