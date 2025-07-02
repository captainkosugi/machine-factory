package com.machine_factotry.kursovaya.controller;

import com.machine_factotry.kursovaya.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class DashboardController {


    private final DashboardService dashboardService;


    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Map<String, Integer> dashboardData = dashboardService.getDashboardData();
        model.addAttribute("EmployeesCount", dashboardData.get("EmployeesCount"));
        model.addAttribute("EquipmentCount", dashboardData.get("EquipmentCount"));
        model.addAttribute("ProductsCount",dashboardData.get("ProductsCount"));
        model.addAttribute("PartnersCount", dashboardData.get("PartnersCount"));
        return "dashboard";
    }
}
