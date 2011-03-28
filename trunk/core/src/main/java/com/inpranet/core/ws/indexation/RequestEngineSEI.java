package com.inpranet.core.ws.indexation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.inpranet.core.model.Document;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;

/**
 * This class was generated by Apache CXF 2.2.7
 * Sun Mar 27 14:46:13 CEST 2011
 * Generated source version: 2.2.7
 * 
 */
 
@WebService(targetNamespace = "http://indexation.inpranet.com/", name = "RequestEngineSEI")
@XmlSeeAlso({ObjectFactory.class})
public interface RequestEngineSEI {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "LaunchRequest", targetNamespace = "http://indexation.inpranet.com/", className = "com.inpranet.core.ws.indexation.LaunchRequest")
    @WebMethod(operationName = "LaunchRequest")
    @ResponseWrapper(localName = "LaunchRequestResponse", targetNamespace = "http://indexation.inpranet.com/", className = "com.inpranet.core.ws.indexation.LaunchRequestResponse")
    public java.util.List<Document> launchRequest(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.util.List<Zone> arg1
    );
}