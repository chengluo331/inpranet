
package com.inpranet.core.ws.indexation;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;


/**
 * <p>Java class for LaunchRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LaunchRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://indexation.inpranet.com/}user" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://indexation.inpranet.com/}zone" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LaunchRequest", propOrder = {
    "arg0",
    "arg1"
})
public class LaunchRequest {

    protected User arg0;
    protected List<Zone> arg1;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setArg0(User value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arg1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArg1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Zone }
     * 
     * 
     */
    public List<Zone> getArg1() {
        if (arg1 == null) {
            arg1 = new ArrayList<Zone>();
        }
        return this.arg1;
    }

}
