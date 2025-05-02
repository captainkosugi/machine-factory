package com.machine_factotry.kursovaya.controller;

import com.machine_factotry.kursovaya.service.PartnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class PartnersController {

    @Autowired
    PartnersService partnersService;

    @GetMapping("/partners")
    public String partners(Model model) {
        List<Map<String, Object>> partners = partnersService.getPartnersData();
        model.addAttribute("partners", partners);
        return "partners";
    }
}
