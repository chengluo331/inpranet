package com.inpranet.frontservice.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="geopos")
public class GeoPos {

	@XmlElement
	private double longitude;
	@XmlElement
	private double latitude;
	@XmlElement
	private Date time;

	public GeoPos() {

	}

	public GeoPos(double longitude, double latitude, Date time) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
	}

	public double getLongitude() {
		return longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public Date getTime() {
		return time;
	}

}
