package com.inpranet.core.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "geoPos", propOrder = {
    "longitude",
    "latitude",
    "time"
})
@XmlRootElement(name="geopos")
public class GeoPos {

	private double longitude;

	private double latitude;
	@XmlSchemaType(name = "dateTime")
	private XMLGregorianCalendar time;

	public GeoPos() {

	}

	public GeoPos(double longitude, double latitude, XMLGregorianCalendar time) {
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


	public XMLGregorianCalendar getTime() {
		return time;
	}

}
