
/*
 * 
 */

package com.inpranet.core.ws.zone;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.7
 * Mon Mar 28 20:53:53 CEST 2011
 * Generated source version: 2.2.7
 * 
 */


@WebServiceClient(name = "ZoneServiceService", 
                  wsdlLocation = "file:/C:/Documents%20and%20Settings/sopra/inpranet/core/src/main/resources/zone.wsdl",
                  targetNamespace = "http://service.zone.inpranet.com/") 
public class ZoneServiceService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://service.zone.inpranet.com/", "ZoneServiceService");
    public final static QName ZoneServicePort = new QName("http://service.zone.inpranet.com/", "ZoneServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Documents%20and%20Settings/sopra/inpranet/core/src/main/resources/zone.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:/C:/Documents%20and%20Settings/sopra/inpranet/core/src/main/resources/zone.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public ZoneServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ZoneServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZoneServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns IZoneService
     */
    @WebEndpoint(name = "ZoneServicePort")
    public IZoneService getZoneServicePort() {
        return super.getPort(ZoneServicePort, IZoneService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IZoneService
     */
    @WebEndpoint(name = "ZoneServicePort")
    public IZoneService getZoneServicePort(WebServiceFeature... features) {
        return super.getPort(ZoneServicePort, IZoneService.class, features);
    }

}
