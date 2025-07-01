package com.machine_factotry.kursovaya.service;


import com.machine_factotry.kursovaya.dto.EmployeeDTO;
import com.machine_factotry.kursovaya.entity.Employee;
import com.machine_factotry.kursovaya.repository.DepartmentRepository;
import com.machine_factotry.kursovaya.repository.EmployeeRepository;
import com.machine_factotry.kursovaya.util.DtoConverter;
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


    public EmployeeService(EmployeeRepository employeeRepository
            , DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    private EmployeeDTO convertToDto(Employee employee) {
        String departmentName = departmentRepository.getDepartmentNameById(
                employee.getDepartmentIt()
        );
        return EmployeeDTO.toDTO(employee, departmentName);
    }

    public List<EmployeeDTO> getEmployeesData() {
        return DtoConverter.convert(
                employeeRepository.getAllEmployees(),
                this::convertToDto
        );
    }

    public List<EmployeeDTO> searchEmployee(String searchTerm) {
        return DtoConverter.convert(
                employeeRepository.searchEmployees("%"+searchTerm+"%"),
                this::convertToDto
        );
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
