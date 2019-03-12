package com.composite.cxf.controller;

import com.alibaba.fastjson.JSONObject;
import com.composite.cxf.entity.Student;
import com.composite.cxf.entity.Students;
import com.composite.cxf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @Produces({MediaType.APPLICATION_JSON + "charset='utf-8'"})
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public String getStudent(@PathVariable("id") Integer id) {
        Student student = studentService.getStudent(id);
        Object json = JSONObject.toJSON(student);
        return json.toString();
    }

    /**
     * @param ids
     * @return
     * @参数：{"ids":{"id":[1,2,3,4]
     */
    @ResponseBody
    @Produces({MediaType.APPLICATION_JSON + "charset='utf-8'"})
    @Consumes({MediaType.APPLICATION_JSON})
    @RequestMapping(value = "gets/{ids}", method = RequestMethod.GET)
    public String getAll(@PathVariable("ids") String ids) {
        Students students = studentService.getAllStudent(ids);
        Object json = JSONObject.toJSON(students);
        return json.toString();
    }
}
