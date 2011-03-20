package com.inpranet.zone.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "zone")
public class Zone {

	@XmlElement
	int id;
	
	public Zone(){
		
	}
	
	
	public Zone(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
