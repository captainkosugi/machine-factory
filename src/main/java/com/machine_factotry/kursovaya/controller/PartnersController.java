package com.machine_factotry.kursovaya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartnersController {

    @GetMapping("/partners")
    public String partners() {
        return "partners";
    }
}
