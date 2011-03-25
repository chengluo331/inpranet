package com.inpranet.core.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "zone", namespace =
    "http://www.inpranet.com/zone")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Zone {

	
	private int idZone;
	
	
	private Interest zoneInterest;
	
	public Zone(int id, Interest zoneInterest) {
		super();
		this.zoneInterest = zoneInterest;
		this.idZone = id;
	}
	
	public Zone() {
		
	}

	@XmlElement
	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
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
