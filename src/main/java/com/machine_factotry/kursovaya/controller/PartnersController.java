package com.machine_factotry.kursovaya.controller;

import com.machine_factotry.kursovaya.dto.PartnerDTO;
import com.machine_factotry.kursovaya.service.PartnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PartnersController {


    private final PartnersService partnersService;


    public PartnersController(PartnersService partnersService) {
        this.partnersService = partnersService;
    }

    @GetMapping("/partners")
    public String partners(
            @RequestParam(name = "search", required = false) String searchTerm,
            Model model) {
        List<PartnerDTO> partners;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            partners = partnersService.searchPartner(searchTerm);
        } else {
            partners = partnersService.getPartnersData();
        }

        model.addAttribute("partners", partners);
        model.addAttribute("searchTerm", searchTerm);
        return "partners";
    }

    @PostMapping("/add-partner")
    public String addPartner(@RequestParam Map<String, String> formData) {
        partnersService.addPartner(formData);
        return "redirect:/partners";
    }

    @PostMapping("/delete-partner")
    public String deletePartner(@RequestParam("partnerName") String partnerName,
                                @RequestParam("partnerType") String partnerType) {
        partnersService.deletePartner(partnerName, partnerType);
        return "redirect:/partners";
    }
}
