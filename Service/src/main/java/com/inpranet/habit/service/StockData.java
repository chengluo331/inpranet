
package com.inpranet.habit.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StockData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StockData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://service.habit.inpranet.com/}user" minOccurs="0"/>
 *         &lt;element name="geoPos" type="{http://service.habit.inpranet.com/}geoPos" minOccurs="0"/>
 *         &lt;element name="zones" type="{http://service.habit.inpranet.com/}zone" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StockData", propOrder = {
    "user",
    "geoPos",
    "zones"
})
public class StockData {

    protected User user;
    protected GeoPos geoPos;
    protected List<Zone> zones;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the geoPos property.
     * 
     * @return
     *     possible object is
     *     {@link GeoPos }
     *     
     */
    public GeoPos getGeoPos() {
        return geoPos;
    }

    /**
     * Sets the value of the geoPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeoPos }
     *     
     */
    public void setGeoPos(GeoPos value) {
        this.geoPos = value;
    }

    /**
     * Gets the value of the zones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Zone }
     * 
     * 
     */
    public List<Zone> getZones() {
        if (zones == null) {
            zones = new ArrayList<Zone>();
        }
        return this.zones;
    }

}
