package com.machine_factotry.kursovaya.controller;

import com.machine_factotry.kursovaya.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @GetMapping("/products")
    public String products(
            @RequestParam(name = "search", required = false) String searchTerm,
            Model model) {
        List<Map<String, Object>> productCategory = productsService.getProductsCategory();
        model.addAttribute("categories", productCategory);

        List<Map<String, Object>> productStatus = productsService.getProductStatus();
        model.addAttribute("statuses", productStatus);

        List<Map<String, Object>> products;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            products = productsService.searchProducts(searchTerm);
        } else {
            products = productsService.getProductsData();
        }

        model.addAttribute("products", products);
        model.addAttribute("searchTerm", searchTerm);
        return "products";
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestParam Map<String, String> formData) {
        productsService.addProduct(formData);
        return "redirect:/products";
    }
}
