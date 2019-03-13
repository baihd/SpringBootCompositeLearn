package com.composite.cxf.service;


import com.composite.cxf.entity.Student;
import com.composite.cxf.entity.Students;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 * WebService接口定义 soap
 */
@WebService
public interface StudentService {

    /**
     * 查找一个学生
     *
     * @param id
     * @return
     */
    @WebMethod
    Student getStudent(@WebParam(name = "id") Integer id);

    /**
     * 查找多个学生
     *
     * @param ids
     * @return
     */
    @WebMethod
    Students getAllStudent(@WebParam(name = "ids") String ids);

}
