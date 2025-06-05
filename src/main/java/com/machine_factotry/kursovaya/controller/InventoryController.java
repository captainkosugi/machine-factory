package com.machine_factotry.kursovaya.controller;

import com.machine_factotry.kursovaya.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/inventory")
    public String inventory(
            @RequestParam(name = "search", required = false) String searchTerm,
            Model model) {
        List<Map<String, Object>> inventoryData;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            inventoryData = inventoryService.searchMovement(searchTerm);
        } else {
            inventoryData = inventoryService.getInventoryData();
        }

        model.addAttribute("inventory", inventoryData);
        model.addAttribute("searchTerm", searchTerm);
        return "inventory";
    }

    @PostMapping("/add-movement")
    public String addMovement(@RequestParam Map<String, String> formData) {
        int partnerId = inventoryService.partnerId(formData);
        int productId = inventoryService.productId(formData.get("movement_name"));

        inventoryService.addMovement(formData, partnerId, productId);
        return "redirect:/inventory";
    }
}
