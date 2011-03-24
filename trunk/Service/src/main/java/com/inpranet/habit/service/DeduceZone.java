
package com.inpranet.habit.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeduceZone complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeduceZone">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://service.habit.inpranet.com/}user" minOccurs="0"/>
 *         &lt;element name="planningHorizon" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="interests" type="{http://service.habit.inpranet.com/}interest" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeduceZone", propOrder = {
    "user",
    "planningHorizon",
    "interests"
})
public class DeduceZone {

    protected User user;
    protected int planningHorizon;
    protected List<Interest> interests;

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
     * Gets the value of the planningHorizon property.
     * 
     */
    public int getPlanningHorizon() {
        return planningHorizon;
    }

    /**
     * Sets the value of the planningHorizon property.
     * 
     */
    public void setPlanningHorizon(int value) {
        this.planningHorizon = value;
    }

    /**
     * Gets the value of the interests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Interest }
     * 
     * 
     */
    public List<Interest> getInterests() {
        if (interests == null) {
            interests = new ArrayList<Interest>();
        }
        return this.interests;
    }

}
