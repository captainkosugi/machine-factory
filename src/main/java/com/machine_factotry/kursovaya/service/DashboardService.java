package com.machine_factotry.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_EMPLOYEE_COUNT = "select count(*) from employees";
    private static final String GET_EQUIPMENT_COUNT = "select count(*) from equipment";
    private static final String GET_PRODUCTS_COUNT = "select sum(p.quantity) from products p";
    private static final String GET_PARTNERS_COUNT = """
    select count(*) + (select count(*) from customers c) from suppliers s
    """;

    private int getEmployeeCount() {
        return jdbcTemplate.queryForObject(GET_EMPLOYEE_COUNT, Integer.class);
    }

    private int getEquipmentCount() {
        return jdbcTemplate.queryForObject(GET_EQUIPMENT_COUNT, Integer.class);
    }

    private int getProductsCount() {
        return jdbcTemplate.queryForObject(GET_PRODUCTS_COUNT, Integer.class);
    }

    private int getPartnersCount() {
        return jdbcTemplate.queryForObject(GET_PARTNERS_COUNT, Integer.class);
    }

    public Map<String, Integer> getDashboardData() {
        Map<String, Integer> data = new HashMap<>();
        data.put("EmployeesCount", getEmployeeCount());
        data.put("EquipmentCount", getEquipmentCount());
        data.put("ProductsCount", getProductsCount());
        data.put("PartnersCount", getPartnersCount());
        return data;
    }
}
