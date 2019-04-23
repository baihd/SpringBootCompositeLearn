package com.composite.service;

import com.composite.domain.Employee;

import java.util.List;
import java.util.Map;

public interface EmpService {

    Map<String, Object> getEmployeeList();

    String postEmployee();

    String putEmployee();

    String deleteEmployee();
}
