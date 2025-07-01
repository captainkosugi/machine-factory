package com.machine_factotry.kursovaya.dto;

import com.machine_factotry.kursovaya.entity.Department;

public class DepartmentDTO {

    private Long id;
    private String department_name;

    public static DepartmentDTO toDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setDepartment_name(department.getDepartment_name());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
