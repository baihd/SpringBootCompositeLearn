package com.composite.cxfserver.service.impl;

import com.composite.cxfserver.base.ResponseBean;
import com.composite.cxfserver.entity.Student;
import com.composite.cxfserver.entity.Students;
import com.composite.cxfserver.service.StudentService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现webservice接口，对外暴露soap
 */
@Service
@WebService(
        serviceName = "StudentService",
        targetNamespace = "http://service.cxfserver.composite.com",
        endpointInterface = "com.composite.cxfserver.service.StudentService")
//绑定SOAP1.2
@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
public class StudentServiceImpl implements StudentService {

    @Override
    public Student getStudent(Integer id) {
        Student student = new Student();
        student.setId(1);
        student.setName("张三");
        student.setAge(20);
        student.setAddress("南京");
        student.setSex("男");
        return student;
    }

    @Override
    public Students getAllStudent(String ids) {
        List<Student> studentList = new ArrayList<>();
        Students students = new Students();
        Student student = new Student();
        student.setId(1);
        student.setName("李四");
        student.setAge(20);
        student.setAddress("南京");
        student.setSex("男");
        studentList.add(student);
        students.setStudents(studentList);
        return students;
    }


}
