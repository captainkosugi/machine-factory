package com.machine_factotry.kursovaya.controller;

import com.machine_factotry.kursovaya.dto.DepartmentDTO;
import com.machine_factotry.kursovaya.dto.EquipmentDTO;
import com.machine_factotry.kursovaya.service.DepartmentService;
import com.machine_factotry.kursovaya.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/equipment")
    public String equipment(
            @RequestParam(name = "search", required = false) String searchTerm,
            Model model) {
        List<DepartmentDTO> departments = departmentService.getDepartmentsData();
        model.addAttribute("departments", departments);

        List<String> equipmentStatus = equipmentService.getEquipmentStatusData();
        model.addAttribute("statuses", equipmentStatus);

        List<EquipmentDTO> equipments;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            equipments = equipmentService.searchEquipment(searchTerm);
        } else {
            equipments = equipmentService.getEquipmentData();
        }


        model.addAttribute("equipment", equipments);
        model.addAttribute("searchTerm", searchTerm);
        return "equipment";
    }

    @PostMapping("/add-equipment")
    public String addEquipment(@RequestParam Map<String, String> formData) {
        String getDepartmentName = formData.get("departmentName");
        int getDepartmentId = departmentService.getDepartmentIdByName(getDepartmentName);

        equipmentService.addEquipment(formData, getDepartmentId);
        return "redirect:/equipment";
    }
}
