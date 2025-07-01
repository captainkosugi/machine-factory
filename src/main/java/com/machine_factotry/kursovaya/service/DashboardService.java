package com.machine_factotry.kursovaya.service;

import com.machine_factotry.kursovaya.repository.DashboardRepository;
import com.machine_factotry.kursovaya.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DashboardService(DashboardRepository dashboardRepository,
                            EmployeeRepository employeeRepository) {
        this.dashboardRepository = dashboardRepository;
        this.employeeRepository = employeeRepository;
    }

    private int getEmployeeCount() {
        return employeeRepository.getCountEmployees();
    }

    private int getEquipmentCount() {
        return dashboardRepository.getCountEquipment();
    }

    private int getProductsCount() {
        return dashboardRepository.getProductsCount();
    }

    private int getPartnersCount() {
        return dashboardRepository.getPartnersCount();
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
