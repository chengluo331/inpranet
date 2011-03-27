
package com.inpranet.core.ws.bus;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Interest;
import com.inpranet.core.model.Zone;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.inpranet.core.ws.bus package. 
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

    private final static QName _GetZoneListFromGeoPosResponse_QNAME = new QName("http://service.frontservice.inpranet.com/", "getZoneListFromGeoPosResponse");
    private final static QName _Zone_QNAME = new QName("http://service.frontservice.inpranet.com/", "zone");
    private final static QName _Geopos_QNAME = new QName("http://service.frontservice.inpranet.com/", "geopos");
    private final static QName _GetZoneListFromGeoPos_QNAME = new QName("http://service.frontservice.inpranet.com/", "getZoneListFromGeoPos");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.inpranet.core.ws.bus
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetZoneListFromGeoPosResponse }
     * 
     */
    public GetZoneListFromGeoPosResponse createGetZoneListFromGeoPosResponse() {
        return new GetZoneListFromGeoPosResponse();
    }

    /**
     * Create an instance of {@link Interest }
     * 
     */
    public Interest createInterest() {
        return new Interest();
    }

    /**
     * Create an instance of {@link GeoPos }
     * 
     */
    public GeoPos createGeoPos() {
        return new GeoPos();
    }

    /**
     * Create an instance of {@link Zone }
     * 
     */
    public Zone createZone() {
        return new Zone();
    }

    /**
     * Create an instance of {@link GetZoneListFromGeoPos }
     * 
     */
    public GetZoneListFromGeoPos createGetZoneListFromGeoPos() {
        return new GetZoneListFromGeoPos();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetZoneListFromGeoPosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.frontservice.inpranet.com/", name = "getZoneListFromGeoPosResponse")
    public JAXBElement<GetZoneListFromGeoPosResponse> createGetZoneListFromGeoPosResponse(GetZoneListFromGeoPosResponse value) {
        return new JAXBElement<GetZoneListFromGeoPosResponse>(_GetZoneListFromGeoPosResponse_QNAME, GetZoneListFromGeoPosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Zone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.frontservice.inpranet.com/", name = "zone")
    public JAXBElement<Zone> createZone(Zone value) {
        return new JAXBElement<Zone>(_Zone_QNAME, Zone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeoPos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.frontservice.inpranet.com/", name = "geopos")
    public JAXBElement<GeoPos> createGeopos(GeoPos value) {
        return new JAXBElement<GeoPos>(_Geopos_QNAME, GeoPos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetZoneListFromGeoPos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.frontservice.inpranet.com/", name = "getZoneListFromGeoPos")
    public JAXBElement<GetZoneListFromGeoPos> createGetZoneListFromGeoPos(GetZoneListFromGeoPos value) {
        return new JAXBElement<GetZoneListFromGeoPos>(_GetZoneListFromGeoPos_QNAME, GetZoneListFromGeoPos.class, null, value);
    }

}
