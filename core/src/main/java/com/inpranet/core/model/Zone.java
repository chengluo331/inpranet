package com.inpranet.core.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zone", propOrder = {
    "idZone",
    "zoneInterest"
})
@XmlRootElement(name="zone")
public class Zone {

	
	private int idZone;
	
	
	private Interest zoneInterest;
	
	public Zone(int id, Interest zoneInterest) {
		super();
		this.zoneInterest = zoneInterest;
		this.idZone = (int) id;
	}
	
	public Zone() {
		
	}

	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public Interest getZoneInterest() {
		return zoneInterest;
	}

	public void setZoneInterest(Interest zoneInterest) {
		this.zoneInterest = zoneInterest;
	}
	
	
}
