package com.composite.cxfclient;

import com.composite.cxfclient.client.Student;
import com.composite.cxfclient.client.StudentService;
import com.composite.cxfclient.client.Students;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class WebServiceCXFClient {

    public static void main(String[] args) {
        JaxWsProxyFactoryBean ps = new JaxWsProxyFactoryBean();
        ps.setAddress("http://127.0.0.1:8082/webservice/api/v1/student/get?wsdl");
        ps.setServiceClass(StudentService.class);
        StudentService studentService = (StudentService) ps.create();
        studentService.getStudent(1);
        Student student = studentService.getStudent(1);
        Students students = studentService.getAllStudent("{'id':[1,2,3,4]}");
        System.out.println(student.toString());
        System.out.println(students.toString());
    }
}
