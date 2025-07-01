package com.machine_factotry.kursovaya.controller;


import com.machine_factotry.kursovaya.dto.DepartmentDTO;
import com.machine_factotry.kursovaya.dto.EmployeeDTO;
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

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/employees")
    public String employees(
            @RequestParam(name = "search", required = false) String searchTerm,
            Model model) {
        List<DepartmentDTO> departments = departmentService.getDepartmentsData();
        model.addAttribute("departments", departments);

        List<EmployeeDTO> employees;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            employees = employeeService.searchEmployee(searchTerm);
        }else {
            employees = employeeService.getEmployeesData();
        }

        model.addAttribute("employees", employees);
        model.addAttribute("searchTerm", searchTerm);
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
