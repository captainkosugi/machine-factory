package com.machine_factotry.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_DEPARTMENTS_DATA =
            "select id as d_id, department_name as d_name from departments";

    private static final String GET_DEPARTMENT_BY_NAME =
            "select id from departments where department_name = ?";

    public List<Map<String, Object>> getDepartmentsData() {
        return jdbcTemplate.queryForList(GET_DEPARTMENTS_DATA);
    }

    public Integer getDepartmentIdByName(String departmentName) {
        return jdbcTemplate.queryForObject(GET_DEPARTMENT_BY_NAME, Integer.class, departmentName);
    }
}
