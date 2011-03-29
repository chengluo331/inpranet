package com.inpranet.core.ws.habit;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Interest;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;

/**
 * This class was generated by Apache CXF 2.2.7
 * Mon Mar 28 20:53:53 CEST 2011
 * Generated source version: 2.2.7
 * 
 */
 
@WebService(targetNamespace = "http://service.habit.inpranet.com/", name = "IHabitService")
@XmlSeeAlso({ObjectFactory.class})
public interface IHabitService {

    @RequestWrapper(localName = "StockData", targetNamespace = "http://service.habit.inpranet.com/", className = "com.inpranet.core.ws.habit.StockData")
    @WebMethod(operationName = "StockData")
    @ResponseWrapper(localName = "StockDataResponse", targetNamespace = "http://service.habit.inpranet.com/", className = "com.inpranet.core.ws.habit.StockDataResponse")
    public void stockData(
        @WebParam(name = "user", targetNamespace = "")
        User user,
        @WebParam(name = "geoPos", targetNamespace = "")
        GeoPos geoPos,
        @WebParam(name = "zones", targetNamespace = "")
        java.util.List<Zone> zones
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "DeduceZone", targetNamespace = "http://service.habit.inpranet.com/", className = "com.inpranet.core.ws.habit.DeduceZone")
    @WebMethod(operationName = "DeduceZone")
    @ResponseWrapper(localName = "DeduceZoneResponse", targetNamespace = "http://service.habit.inpranet.com/", className = "com.inpranet.core.ws.habit.DeduceZoneResponse")
    public java.util.List<Zone> deduceZone(
        @WebParam(name = "user", targetNamespace = "")
        User user,
        @WebParam(name = "planningHorizon", targetNamespace = "")
        int planningHorizon,
        @WebParam(name = "interests", targetNamespace = "")
        java.util.List<Interest> interests
    );
}
