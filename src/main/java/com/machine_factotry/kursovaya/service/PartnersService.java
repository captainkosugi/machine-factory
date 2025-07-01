package com.machine_factotry.kursovaya.service;


import com.machine_factotry.kursovaya.dto.PartnerDTO;
import com.machine_factotry.kursovaya.repository.CustomerRepository;
import com.machine_factotry.kursovaya.repository.PartnerRepository;
import com.machine_factotry.kursovaya.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PartnersService {

    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;
    private final PartnerRepository partnerRepository;

    public PartnersService(CustomerRepository customerRepository,
                           SupplierRepository supplierRepository,
                           PartnerRepository partnerRepository) {
        this.customerRepository = customerRepository;
        this.supplierRepository = supplierRepository;
        this.partnerRepository = partnerRepository;
    }

    public List<PartnerDTO> searchPartner(String searchTerm) {
        return partnerRepository.searchPartner("%"+searchTerm+"%");
    }

    public void addPartner(Map<String, String> formData) {
        if (formData.get("partner_type").equals("Покупатель")) {
            customerRepository.addCustomer(
                    formData.get("partner_name"),
                    formData.get("partner_phone"),
                    formData.get("partner_email"));
        } else {
            supplierRepository.addSupplier(
                    formData.get("partner_name"),
                    formData.get("partner_phone"),
                    formData.get("partner_email"));
        }
    }

    public List<PartnerDTO> getPartnersData() {
        return partnerRepository.getAllPartners();
    }
}
