package com.machine_factotry.kursovaya.dto;


import com.machine_factotry.kursovaya.entity.Employee;


import java.time.format.DateTimeFormatter;

public class EmployeeDTO {

    private Long id;
    private String name;
    private String position;
    private String departmentName;
    private Double salary;
    private String hireDate;

    public static EmployeeDTO toDTO(Employee employee, String departmentName) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setPosition(employee.getPosition());
        dto.setDepartmentName(departmentName);
        dto.setSalary(employee.getSalary());
        dto.setHireDate(employee.getHireDate()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
}
