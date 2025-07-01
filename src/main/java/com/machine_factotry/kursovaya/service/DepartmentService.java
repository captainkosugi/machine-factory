package com.machine_factotry.kursovaya.service;

import com.machine_factotry.kursovaya.dto.DepartmentDTO;
import com.machine_factotry.kursovaya.entity.Department;
import com.machine_factotry.kursovaya.repository.DepartmentRepository;
import com.machine_factotry.kursovaya.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;


    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    private DepartmentDTO convertToDto(Department department) {
        return DepartmentDTO.toDTO(department);
    }

    public List<DepartmentDTO> getDepartmentsData() {
        return DtoConverter.convert(
                departmentRepository.getAllDepartments(),
                this::convertToDto
        );
    }

    public Integer getDepartmentIdByName(String departmentName) {
        return departmentRepository.getDepartmentIdByName(departmentName);
    }
}
