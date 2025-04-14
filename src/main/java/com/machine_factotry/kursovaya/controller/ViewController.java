package com.machine_factotry.kursovaya.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/employees")
    public String employees() {
        return "employees";
    }

    @GetMapping("/equipment")
    public String equipment() {
        return "equipment";
    }

    @GetMapping("/inventory")
    public String inventory() {
        return "inventory";
    }

    @GetMapping("/partners")
    public String partners() {
        return "partners";
    }

    @GetMapping("/products")
    public String products() {

        return "products";
    }

    @GetMapping("/reports")
    public String reports() {
        return "reports";
    }

}
