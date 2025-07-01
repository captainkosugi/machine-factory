package com.machine_factotry.kursovaya.service;

import com.machine_factotry.kursovaya.dto.DepartmentDTO;
import com.machine_factotry.kursovaya.entity.Department;
import com.machine_factotry.kursovaya.repository.DepartmentRepository;
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

    public List<DepartmentDTO> getDepartmentsData() {
        Iterable<Department> departmentIterable =  departmentRepository.getAllDepartments();
        List<DepartmentDTO> departmentList = new ArrayList<>();

        for (Department department : departmentIterable) {
            DepartmentDTO dto = DepartmentDTO.toDTO(department);
            departmentList.add(dto);
        }
        return departmentList;
    }

    public Integer getDepartmentIdByName(String departmentName) {
        return departmentRepository.getDepartmentIdByName(departmentName);
    }
}
