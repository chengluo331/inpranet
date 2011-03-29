
package com.inpranet.core.ws.zone;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.inpranet.core.model.Interest;
import com.inpranet.core.model.Zone;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.inpranet.core.ws.zone package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetZoneListFromPosResponse_QNAME = new QName("http://service.zone.inpranet.com/", "getZoneListFromPosResponse");
    private final static QName _GetZoneListFromPos_QNAME = new QName("http://service.zone.inpranet.com/", "getZoneListFromPos");
    private final static QName _Zone_QNAME = new QName("http://www.inpranet.com/zone", "zone");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.inpranet.core.ws.zone
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetZoneListFromPos }
     * 
     */
    public GetZoneListFromPos createGetZoneListFromPos() {
        return new GetZoneListFromPos();
    }

    /**
     * Create an instance of {@link Zone }
     * 
     */
    public Zone createZone() {
        return new Zone();
    }

    /**
     * Create an instance of {@link Interest }
     * 
     */
    public Interest createInterest() {
        return new Interest();
    }

    /**
     * Create an instance of {@link GetZoneListFromPosResponse }
     * 
     */
    public GetZoneListFromPosResponse createGetZoneListFromPosResponse() {
        return new GetZoneListFromPosResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetZoneListFromPosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.zone.inpranet.com/", name = "getZoneListFromPosResponse")
    public JAXBElement<GetZoneListFromPosResponse> createGetZoneListFromPosResponse(GetZoneListFromPosResponse value) {
        return new JAXBElement<GetZoneListFromPosResponse>(_GetZoneListFromPosResponse_QNAME, GetZoneListFromPosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetZoneListFromPos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.zone.inpranet.com/", name = "getZoneListFromPos")
    public JAXBElement<GetZoneListFromPos> createGetZoneListFromPos(GetZoneListFromPos value) {
        return new JAXBElement<GetZoneListFromPos>(_GetZoneListFromPos_QNAME, GetZoneListFromPos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Zone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.inpranet.com/zone", name = "zone")
    public JAXBElement<Zone> createZone(Zone value) {
        return new JAXBElement<Zone>(_Zone_QNAME, Zone.class, null, value);
    }

}
