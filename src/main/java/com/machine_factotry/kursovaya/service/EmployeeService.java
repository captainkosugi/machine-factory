package com.machine_factotry.kursovaya.service;


import com.machine_factotry.kursovaya.dto.EmployeeDTO;
import com.machine_factotry.kursovaya.entity.Employee;
import com.machine_factotry.kursovaya.repository.DepartmentRepository;
import com.machine_factotry.kursovaya.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository
            , DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<EmployeeDTO> getEmployeesData() {
        Iterable<Employee> employeeIterable = employeeRepository.getAllEmployees();
        List<EmployeeDTO> employeeList = new ArrayList<>();

        for (Employee employee : employeeIterable) {
            EmployeeDTO dto = EmployeeDTO.toDTO(employee,
                    departmentRepository.getDepartmentNameById(employee.getDepartmentIt()));
            employeeList.add(dto);

        }
        return employeeList;
    }

    public List<EmployeeDTO> searchEmployee(String searchTerm) {
        Iterable<Employee> employeeIterable =
                employeeRepository.searchEmployees("%" + searchTerm + "%");
        List<EmployeeDTO> employeeList = new ArrayList<>();

        for (Employee employee : employeeIterable) {
            EmployeeDTO dto = EmployeeDTO.toDTO(employee,
                    departmentRepository.getDepartmentNameById(employee.getDepartmentIt()));
            employeeList.add(dto);
        }
        return employeeList;
    }

    public void addEmployee(Map<String, String> formData, int departmentId) {

        String employeeName = formData.get("em_name");
        String employeePosition = formData.get("em_position");
        String employeeSalary = formData.get("em_salary");
        String employeeHireDate = formData.get("em_hire_date");

        employeeRepository.addNewEmployee(
                employeeName,
                employeePosition,
                departmentId,
                Double.parseDouble(employeeSalary),
                LocalDate.parse(employeeHireDate));
    }


}
