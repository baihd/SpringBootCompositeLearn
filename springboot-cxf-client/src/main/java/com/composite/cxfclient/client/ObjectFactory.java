
package com.composite.cxfclient.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.composite.cxfclient.client package. 
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

    private final static QName _Student_QNAME = new QName("http://service.cxf.composite.com/", "Student");
    private final static QName _Students_QNAME = new QName("http://service.cxf.composite.com/", "Students");
    private final static QName _GetAllStudentOperation_QNAME = new QName("http://service.cxf.composite.com/", "getAllStudentOperation");
    private final static QName _GetAllStudentOperationResponse_QNAME = new QName("http://service.cxf.composite.com/", "getAllStudentOperationResponse");
    private final static QName _GetStudentOperation_QNAME = new QName("http://service.cxf.composite.com/", "getStudentOperation");
    private final static QName _GetStudentOperationResponse_QNAME = new QName("http://service.cxf.composite.com/", "getStudentOperationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.composite.cxfclient.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link Students }
     * 
     */
    public Students createStudents() {
        return new Students();
    }

    /**
     * Create an instance of {@link GetAllStudentOperation }
     * 
     */
    public GetAllStudentOperation createGetAllStudentOperation() {
        return new GetAllStudentOperation();
    }

    /**
     * Create an instance of {@link GetAllStudentOperationResponse }
     * 
     */
    public GetAllStudentOperationResponse createGetAllStudentOperationResponse() {
        return new GetAllStudentOperationResponse();
    }

    /**
     * Create an instance of {@link GetStudentOperation }
     * 
     */
    public GetStudentOperation createGetStudentOperation() {
        return new GetStudentOperation();
    }

    /**
     * Create an instance of {@link GetStudentOperationResponse }
     * 
     */
    public GetStudentOperationResponse createGetStudentOperationResponse() {
        return new GetStudentOperationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Student }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Student }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxf.composite.com/", name = "Student")
    public JAXBElement<Student> createStudent(Student value) {
        return new JAXBElement<Student>(_Student_QNAME, Student.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Students }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Students }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxf.composite.com/", name = "Students")
    public JAXBElement<Students> createStudents(Students value) {
        return new JAXBElement<Students>(_Students_QNAME, Students.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStudentOperation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllStudentOperation }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxf.composite.com/", name = "getAllStudentOperation")
    public JAXBElement<GetAllStudentOperation> createGetAllStudentOperation(GetAllStudentOperation value) {
        return new JAXBElement<GetAllStudentOperation>(_GetAllStudentOperation_QNAME, GetAllStudentOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStudentOperationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllStudentOperationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxf.composite.com/", name = "getAllStudentOperationResponse")
    public JAXBElement<GetAllStudentOperationResponse> createGetAllStudentOperationResponse(GetAllStudentOperationResponse value) {
        return new JAXBElement<GetAllStudentOperationResponse>(_GetAllStudentOperationResponse_QNAME, GetAllStudentOperationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentOperation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStudentOperation }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxf.composite.com/", name = "getStudentOperation")
    public JAXBElement<GetStudentOperation> createGetStudentOperation(GetStudentOperation value) {
        return new JAXBElement<GetStudentOperation>(_GetStudentOperation_QNAME, GetStudentOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentOperationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStudentOperationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxf.composite.com/", name = "getStudentOperationResponse")
    public JAXBElement<GetStudentOperationResponse> createGetStudentOperationResponse(GetStudentOperationResponse value) {
        return new JAXBElement<GetStudentOperationResponse>(_GetStudentOperationResponse_QNAME, GetStudentOperationResponse.class, null, value);
    }

}
