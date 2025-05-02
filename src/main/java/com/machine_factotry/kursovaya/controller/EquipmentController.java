package com.machine_factotry.kursovaya.controller;

import com.machine_factotry.kursovaya.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/equipment")
    public String equipment(Model model) {
        List<Map<String, Object>> equipments = equipmentService.getEquipmentData();
        model.addAttribute("equipment", equipments);
        return "equipment";
    }
}
