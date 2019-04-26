
package com.composite.cxfserver;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.composite.cxfserver package. 
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

    private final static QName _AcceptAccidentOperation_QNAME = new QName("http://service.cxfserver.composite.com/", "acceptAccidentOperation");
    private final static QName _AcceptAccidentOperationResponse_QNAME = new QName("http://service.cxfserver.composite.com/", "acceptAccidentOperationResponse");
    private final static QName _DeleteAccidentOperation_QNAME = new QName("http://service.cxfserver.composite.com/", "deleteAccidentOperation");
    private final static QName _DeleteAccidentOperationResponse_QNAME = new QName("http://service.cxfserver.composite.com/", "deleteAccidentOperationResponse");
    private final static QName _UpdateAccidentOperation_QNAME = new QName("http://service.cxfserver.composite.com/", "updateAccidentOperation");
    private final static QName _UpdateAccidentOperationResponse_QNAME = new QName("http://service.cxfserver.composite.com/", "updateAccidentOperationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.composite.cxfserver
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AcceptAccidentOperation }
     * 
     */
    public AcceptAccidentOperation createAcceptAccidentOperation() {
        return new AcceptAccidentOperation();
    }

    /**
     * Create an instance of {@link AcceptAccidentOperationResponse }
     * 
     */
    public AcceptAccidentOperationResponse createAcceptAccidentOperationResponse() {
        return new AcceptAccidentOperationResponse();
    }

    /**
     * Create an instance of {@link DeleteAccidentOperation }
     * 
     */
    public DeleteAccidentOperation createDeleteAccidentOperation() {
        return new DeleteAccidentOperation();
    }

    /**
     * Create an instance of {@link DeleteAccidentOperationResponse }
     * 
     */
    public DeleteAccidentOperationResponse createDeleteAccidentOperationResponse() {
        return new DeleteAccidentOperationResponse();
    }

    /**
     * Create an instance of {@link UpdateAccidentOperation }
     * 
     */
    public UpdateAccidentOperation createUpdateAccidentOperation() {
        return new UpdateAccidentOperation();
    }

    /**
     * Create an instance of {@link UpdateAccidentOperationResponse }
     * 
     */
    public UpdateAccidentOperationResponse createUpdateAccidentOperationResponse() {
        return new UpdateAccidentOperationResponse();
    }

    /**
     * Create an instance of {@link ResponseBean }
     * 
     */
    public ResponseBean createResponseBean() {
        return new ResponseBean();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptAccidentOperation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AcceptAccidentOperation }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxfserver.composite.com/", name = "acceptAccidentOperation")
    public JAXBElement<AcceptAccidentOperation> createAcceptAccidentOperation(AcceptAccidentOperation value) {
        return new JAXBElement<AcceptAccidentOperation>(_AcceptAccidentOperation_QNAME, AcceptAccidentOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptAccidentOperationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AcceptAccidentOperationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxfserver.composite.com/", name = "acceptAccidentOperationResponse")
    public JAXBElement<AcceptAccidentOperationResponse> createAcceptAccidentOperationResponse(AcceptAccidentOperationResponse value) {
        return new JAXBElement<AcceptAccidentOperationResponse>(_AcceptAccidentOperationResponse_QNAME, AcceptAccidentOperationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAccidentOperation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteAccidentOperation }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxfserver.composite.com/", name = "deleteAccidentOperation")
    public JAXBElement<DeleteAccidentOperation> createDeleteAccidentOperation(DeleteAccidentOperation value) {
        return new JAXBElement<DeleteAccidentOperation>(_DeleteAccidentOperation_QNAME, DeleteAccidentOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAccidentOperationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteAccidentOperationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxfserver.composite.com/", name = "deleteAccidentOperationResponse")
    public JAXBElement<DeleteAccidentOperationResponse> createDeleteAccidentOperationResponse(DeleteAccidentOperationResponse value) {
        return new JAXBElement<DeleteAccidentOperationResponse>(_DeleteAccidentOperationResponse_QNAME, DeleteAccidentOperationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAccidentOperation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateAccidentOperation }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxfserver.composite.com/", name = "updateAccidentOperation")
    public JAXBElement<UpdateAccidentOperation> createUpdateAccidentOperation(UpdateAccidentOperation value) {
        return new JAXBElement<UpdateAccidentOperation>(_UpdateAccidentOperation_QNAME, UpdateAccidentOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAccidentOperationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateAccidentOperationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxfserver.composite.com/", name = "updateAccidentOperationResponse")
    public JAXBElement<UpdateAccidentOperationResponse> createUpdateAccidentOperationResponse(UpdateAccidentOperationResponse value) {
        return new JAXBElement<UpdateAccidentOperationResponse>(_UpdateAccidentOperationResponse_QNAME, UpdateAccidentOperationResponse.class, null, value);
    }

}
