
package com.inpranet.habit.service;

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
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="planningHorizon" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="interest" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "userId",
    "planningHorizon",
    "interest"
})
public class DeduceZone {

    protected int userId;
    protected int planningHorizon;
    protected int interest;

    /**
     * Gets the value of the userId property.
     * 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(int value) {
        this.userId = value;
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
     * Gets the value of the interest property.
     * 
     */
    public int getInterest() {
        return interest;
    }

    /**
     * Sets the value of the interest property.
     * 
     */
    public void setInterest(int value) {
        this.interest = value;
    }

}
