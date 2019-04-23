package com.composite.service;

import com.composite.domain.Employee;

import java.util.List;

public interface EmpService {

    List<Employee> getEmployeeList();

    String postEmployee();

    String putEmployee();

    String deleteEmployee();
}
