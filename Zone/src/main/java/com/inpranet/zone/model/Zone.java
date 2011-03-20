package com.inpranet.zone.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "zone")
public class Zone {

	@XmlElement
	String id;
	
	public Zone(){
		
	}
	
	public Zone(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}