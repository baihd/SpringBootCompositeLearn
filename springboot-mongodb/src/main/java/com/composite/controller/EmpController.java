package com.composite.controller;

import com.composite.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/employees")
public class EmpController {

    @Autowired
    private EmpService empService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, Object> getEmployeeList() {
        return empService.getEmployeeList();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postEmployee() {
        return empService.postEmployee();
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String putEmployee() {
        return empService.putEmployee();
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public String deleteEmployee() {
        return empService.deleteEmployee();
    }

}
