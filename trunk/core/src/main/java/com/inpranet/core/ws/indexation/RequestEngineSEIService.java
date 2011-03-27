
/*
 * 
 */

package com.inpranet.core.ws.indexation;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.7
 * Sun Mar 27 14:46:13 CEST 2011
 * Generated source version: 2.2.7
 * 
 */


@WebServiceClient(name = "RequestEngineSEIService", 
                  wsdlLocation = "file:/home/yoann/workspace/inpranet4/core/src/main/resources/indexation.wsdl",
                  targetNamespace = "http://indexation.inpranet.com/") 
public class RequestEngineSEIService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://indexation.inpranet.com/", "RequestEngineSEIService");
    public final static QName RequestEngineSEIPort = new QName("http://indexation.inpranet.com/", "RequestEngineSEIPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/yoann/workspace/inpranet4/core/src/main/resources/indexation.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from file:/home/yoann/workspace/inpranet4/core/src/main/resources/indexation.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public RequestEngineSEIService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public RequestEngineSEIService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RequestEngineSEIService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns RequestEngineSEI
     */
    @WebEndpoint(name = "RequestEngineSEIPort")
    public RequestEngineSEI getRequestEngineSEIPort() {
        return super.getPort(RequestEngineSEIPort, RequestEngineSEI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RequestEngineSEI
     */
    @WebEndpoint(name = "RequestEngineSEIPort")
    public RequestEngineSEI getRequestEngineSEIPort(WebServiceFeature... features) {
        return super.getPort(RequestEngineSEIPort, RequestEngineSEI.class, features);
    }

}
