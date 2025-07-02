package com.machine_factotry.kursovaya.service;


import com.machine_factotry.kursovaya.dto.PartnerDTO;
import com.machine_factotry.kursovaya.repository.CustomerRepository;
import com.machine_factotry.kursovaya.repository.PartnerRepository;
import com.machine_factotry.kursovaya.repository.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PartnersService {

    private static final Logger log = LoggerFactory.getLogger(PartnersService.class);
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
        log.info("Processing partner search for {}...", searchTerm);
        return partnerRepository.searchPartner("%"+searchTerm+"%");
    }

    public void addPartner(Map<String, String> formData) {
        log.info("Adding new partner {}...", formData.get("partner_name"));
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

    public void deletePartner(String partnerName, String partnerType) {
        log.info("Deleting partner {}...", partnerName);
        if (partnerType.equals("Покупатель")) {
            customerRepository.deleteCustomer(partnerName);
        } else {
            supplierRepository.deleteSupplier(partnerName);
        }
    }

    public List<PartnerDTO> getPartnersData() {
        log.info("Getting all partners...");
        return partnerRepository.getAllPartners();
    }
}
