package com.email.management.system.app.controller;

import com.email.management.system.app.model.Employee;
import com.email.management.system.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addemployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/getemployee")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}