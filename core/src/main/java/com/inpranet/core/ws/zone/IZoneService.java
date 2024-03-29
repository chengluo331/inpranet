package com.inpranet.core.ws.zone;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.inpranet.core.model.Zone;

/**
 * This class was generated by Apache CXF 2.2.7
 * Mon Mar 28 20:53:52 CEST 2011
 * Generated source version: 2.2.7
 * 
 */
 
@WebService(targetNamespace = "http://service.zone.inpranet.com/", name = "IZoneService")
@XmlSeeAlso({ObjectFactory.class})
public interface IZoneService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getZoneListFromPos", targetNamespace = "http://service.zone.inpranet.com/", className = "com.inpranet.core.ws.zone.GetZoneListFromPos")
    @WebMethod
    @ResponseWrapper(localName = "getZoneListFromPosResponse", targetNamespace = "http://service.zone.inpranet.com/", className = "com.inpranet.core.ws.zone.GetZoneListFromPosResponse")
    public java.util.List<Zone> getZoneListFromPos(
        @WebParam(name = "longitude", targetNamespace = "")
        double longitude,
        @WebParam(name = "latitude", targetNamespace = "")
        double latitude
    );
}
