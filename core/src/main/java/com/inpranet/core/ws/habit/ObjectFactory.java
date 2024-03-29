
package com.inpranet.core.ws.habit;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Interest;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.inpranet.core.ws.habit package. 
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

    private final static QName _DeduceZoneResponse_QNAME = new QName("http://service.habit.inpranet.com/", "DeduceZoneResponse");
    private final static QName _StockDataResponse_QNAME = new QName("http://service.habit.inpranet.com/", "StockDataResponse");
    private final static QName _DeduceZone_QNAME = new QName("http://service.habit.inpranet.com/", "DeduceZone");
    private final static QName _Geopos_QNAME = new QName("http://service.habit.inpranet.com/", "geopos");
    private final static QName _StockData_QNAME = new QName("http://service.habit.inpranet.com/", "StockData");
    private final static QName _Zone_QNAME = new QName("http://www.inpranet.com/zone", "zone");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.inpranet.core.ws.habit
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeduceZone }
     * 
     */
    public DeduceZone createDeduceZone() {
        return new DeduceZone();
    }

    /**
     * Create an instance of {@link Zone }
     * 
     */
    public Zone createZone() {
        return new Zone();
    }

    /**
     * Create an instance of {@link GeoPos }
     * 
     */
    public GeoPos createGeoPos() {
        return new GeoPos();
    }

    /**
     * Create an instance of {@link DeduceZoneResponse }
     * 
     */
    public DeduceZoneResponse createDeduceZoneResponse() {
        return new DeduceZoneResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link StockData }
     * 
     */
    public StockData createStockData() {
        return new StockData();
    }

    /**
     * Create an instance of {@link Interest }
     * 
     */
    public Interest createInterest() {
        return new Interest();
    }

    /**
     * Create an instance of {@link StockDataResponse }
     * 
     */
    public StockDataResponse createStockDataResponse() {
        return new StockDataResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeduceZoneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.habit.inpranet.com/", name = "DeduceZoneResponse")
    public JAXBElement<DeduceZoneResponse> createDeduceZoneResponse(DeduceZoneResponse value) {
        return new JAXBElement<DeduceZoneResponse>(_DeduceZoneResponse_QNAME, DeduceZoneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StockDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.habit.inpranet.com/", name = "StockDataResponse")
    public JAXBElement<StockDataResponse> createStockDataResponse(StockDataResponse value) {
        return new JAXBElement<StockDataResponse>(_StockDataResponse_QNAME, StockDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeduceZone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.habit.inpranet.com/", name = "DeduceZone")
    public JAXBElement<DeduceZone> createDeduceZone(DeduceZone value) {
        return new JAXBElement<DeduceZone>(_DeduceZone_QNAME, DeduceZone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeoPos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.habit.inpranet.com/", name = "geopos")
    public JAXBElement<GeoPos> createGeopos(GeoPos value) {
        return new JAXBElement<GeoPos>(_Geopos_QNAME, GeoPos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StockData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.habit.inpranet.com/", name = "StockData")
    public JAXBElement<StockData> createStockData(StockData value) {
        return new JAXBElement<StockData>(_StockData_QNAME, StockData.class, null, value);
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
