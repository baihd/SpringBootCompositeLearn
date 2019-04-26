package com.composite.cxfserver.service;


import com.composite.cxfserver.entity.Student;
import com.composite.cxfserver.entity.Students;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


/**
 * WebService接口定义soap
 */
@WebService
public interface StudentService {

    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "getStudentAction", operationName = "getStudentOperation")
    //定义返回值的名称
    @WebResult(name = "getStudentResult")
    Student getStudent(@WebParam Integer id);

    //action定义一个soapAction="access"用于找到这个方法以执行
    //operationName指定与此方法相匹配的wsdl:operation的名称
    @WebMethod(action = "getAllStudentAction", operationName = "getAllStudentOperation")
    //定义返回值的名称
    @WebResult(name = "getAllStudentResult")
    Students getAllStudent(@WebParam String ids);

}
