
package com.inpranet.core.ws.bus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.inpranet.core.model.GeoPos;


/**
 * <p>Java class for getZoneListFromGeoPos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getZoneListFromGeoPos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="geopos" type="{http://service.frontservice.inpranet.com/}geoPos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getZoneListFromGeoPos", propOrder = {
    "geopos"
})
public class GetZoneListFromGeoPos {

    protected GeoPos geopos;

    /**
     * Gets the value of the geopos property.
     * 
     * @return
     *     possible object is
     *     {@link GeoPos }
     *     
     */
    public GeoPos getGeopos() {
        return geopos;
    }

    /**
     * Sets the value of the geopos property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeoPos }
     *     
     */
    public void setGeopos(GeoPos value) {
        this.geopos = value;
    }

}
