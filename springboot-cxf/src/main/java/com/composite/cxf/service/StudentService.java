package com.composite.cxf.service;


import com.composite.cxf.entity.Student;
import com.composite.cxf.entity.Students;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


/**
 * WebService接口定义 soap
 */
@WebService
public interface StudentService {

    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "getStudentAction", operationName = "getStudentOperation")
    //定义返回值的名称
    @WebResult(name = "resultStudent")
    Student getStudent(@WebParam(name = "id", targetNamespace = "http://service.cxf.composite.com/") Integer id);

    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "getAllStudentAction", operationName = "getAllStudentOperation")
    //定义返回值的名称
    @WebResult(name = "resultAllStudent")
    Students getAllStudent(@WebParam(name = "ids", targetNamespace = "http://service.cxf.composite.com/") String ids);

}
