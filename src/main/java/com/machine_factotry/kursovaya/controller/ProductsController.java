package com.machine_factotry.kursovaya.controller;

import com.machine_factotry.kursovaya.dto.ProductDTO;
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

    private final ProductsService productsService;


    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public String products(
            @RequestParam(name = "search", required = false) String searchTerm,
            Model model) {
        List<String> productCategory = productsService.getProductsCategory();
        model.addAttribute("categories", productCategory);

        List<String> productStatus = productsService.getProductStatus();
        model.addAttribute("statuses", productStatus);

        List<ProductDTO> products;
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

    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam("id") long id) {
        productsService.deleteProduct(id);
        return "redirect:/products";
    }
}
