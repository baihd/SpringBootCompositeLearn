
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
    private final static QName _GetAllStudent_QNAME = new QName("http://service.cxf.composite.com/", "getAllStudent");
    private final static QName _GetAllStudentResponse_QNAME = new QName("http://service.cxf.composite.com/", "getAllStudentResponse");
    private final static QName _GetStudent_QNAME = new QName("http://service.cxf.composite.com/", "getStudent");
    private final static QName _GetStudentResponse_QNAME = new QName("http://service.cxf.composite.com/", "getStudentResponse");

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
     * Create an instance of {@link GetAllStudent }
     * 
     */
    public GetAllStudent createGetAllStudent() {
        return new GetAllStudent();
    }

    /**
     * Create an instance of {@link GetAllStudentResponse }
     * 
     */
    public GetAllStudentResponse createGetAllStudentResponse() {
        return new GetAllStudentResponse();
    }

    /**
     * Create an instance of {@link GetStudent }
     * 
     */
    public GetStudent createGetStudent() {
        return new GetStudent();
    }

    /**
     * Create an instance of {@link GetStudentResponse }
     * 
     */
    public GetStudentResponse createGetStudentResponse() {
        return new GetStudentResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStudent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllStudent }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxf.composite.com/", name = "getAllStudent")
    public JAXBElement<GetAllStudent> createGetAllStudent(GetAllStudent value) {
        return new JAXBElement<GetAllStudent>(_GetAllStudent_QNAME, GetAllStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStudentResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllStudentResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxf.composite.com/", name = "getAllStudentResponse")
    public JAXBElement<GetAllStudentResponse> createGetAllStudentResponse(GetAllStudentResponse value) {
        return new JAXBElement<GetAllStudentResponse>(_GetAllStudentResponse_QNAME, GetAllStudentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStudent }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxf.composite.com/", name = "getStudent")
    public JAXBElement<GetStudent> createGetStudent(GetStudent value) {
        return new JAXBElement<GetStudent>(_GetStudent_QNAME, GetStudent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStudentResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.cxf.composite.com/", name = "getStudentResponse")
    public JAXBElement<GetStudentResponse> createGetStudentResponse(GetStudentResponse value) {
        return new JAXBElement<GetStudentResponse>(_GetStudentResponse_QNAME, GetStudentResponse.class, null, value);
    }

}
