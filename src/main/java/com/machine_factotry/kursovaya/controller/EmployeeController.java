package com.machine_factotry.kursovaya.controller;


import com.machine_factotry.kursovaya.service.DepartmentService;
import com.machine_factotry.kursovaya.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/employees")
    public String employees(Model model) {
        List<Map<String, Object>> departments = departmentService.getDepartmentsData();
        model.addAttribute("departments", departments);

        List<Map<String, Object>> employees = employeeService.getEmployeesData();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@RequestParam Map<String, String> formData) {
        String departmentName = formData.get("departmentName");
        int getDepartmentId = departmentService.getDepartmentIdByName(departmentName);

        employeeService.addEmployee(formData, getDepartmentId);
        return "redirect:/employees";
    }
}
