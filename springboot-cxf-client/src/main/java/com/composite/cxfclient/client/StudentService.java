package com.composite.cxfclient.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.0
 * 2019-03-13T17:14:58.836+08:00
 * Generated source version: 3.3.0
 *
 */
@WebService(targetNamespace = "http://service.cxf.composite.com/", name = "StudentService")
@XmlSeeAlso({ObjectFactory.class})
public interface StudentService {

    @WebMethod
    @RequestWrapper(localName = "getStudent", targetNamespace = "http://service.cxf.composite.com/", className = "com.composite.cxfclient.client.GetStudent")
    @ResponseWrapper(localName = "getStudentResponse", targetNamespace = "http://service.cxf.composite.com/", className = "com.composite.cxfclient.client.GetStudentResponse")
    @WebResult(name = "return", targetNamespace = "")
    public Student getStudent(
            @WebParam(name = "id", targetNamespace = "")
                    Integer id
    );

    @WebMethod
    @RequestWrapper(localName = "getAllStudent", targetNamespace = "http://service.cxf.composite.com/", className = "com.composite.cxfclient.client.GetAllStudent")
    @ResponseWrapper(localName = "getAllStudentResponse", targetNamespace = "http://service.cxf.composite.com/", className = "com.composite.cxfclient.client.GetAllStudentResponse")
    @WebResult(name = "return", targetNamespace = "")
    public Students getAllStudent(
            @WebParam(name = "ids", targetNamespace = "")
                    String ids
    );
}
