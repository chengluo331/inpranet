
package com.inpranet.core.ws.indexation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.inpranet.core.model.Document;
import com.inpranet.core.model.Interest;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.inpranet.core.ws.indexation package. 
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

    private final static QName _Zone_QNAME = new QName("http://indexation.inpranet.com/", "zone");
    private final static QName _LaunchRequest_QNAME = new QName("http://indexation.inpranet.com/", "LaunchRequest");
    private final static QName _LaunchRequestResponse_QNAME = new QName("http://indexation.inpranet.com/", "LaunchRequestResponse");
    private final static QName _Document_QNAME = new QName("http://indexation.inpranet.com/", "document");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.inpranet.core.ws.indexation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Zone }
     * 
     */
    public Zone createZone() {
        return new Zone();
    }

    /**
     * Create an instance of {@link LaunchRequest }
     * 
     */
    public LaunchRequest createLaunchRequest() {
        return new LaunchRequest();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Interest }
     * 
     */
    public Interest createInterest() {
        return new Interest();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link LaunchRequestResponse }
     * 
     */
    public LaunchRequestResponse createLaunchRequestResponse() {
        return new LaunchRequestResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Zone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://indexation.inpranet.com/", name = "zone")
    public JAXBElement<Zone> createZone(Zone value) {
        return new JAXBElement<Zone>(_Zone_QNAME, Zone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LaunchRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://indexation.inpranet.com/", name = "LaunchRequest")
    public JAXBElement<LaunchRequest> createLaunchRequest(LaunchRequest value) {
        return new JAXBElement<LaunchRequest>(_LaunchRequest_QNAME, LaunchRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LaunchRequestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://indexation.inpranet.com/", name = "LaunchRequestResponse")
    public JAXBElement<LaunchRequestResponse> createLaunchRequestResponse(LaunchRequestResponse value) {
        return new JAXBElement<LaunchRequestResponse>(_LaunchRequestResponse_QNAME, LaunchRequestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Document }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://indexation.inpranet.com/", name = "document")
    public JAXBElement<Document> createDocument(Document value) {
        return new JAXBElement<Document>(_Document_QNAME, Document.class, null, value);
    }

}
