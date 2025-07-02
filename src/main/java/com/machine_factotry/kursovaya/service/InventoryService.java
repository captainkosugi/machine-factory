package com.machine_factotry.kursovaya.service;

import com.machine_factotry.kursovaya.dto.GoodsMovementDTO;
import com.machine_factotry.kursovaya.entity.GoodsMovement;
import com.machine_factotry.kursovaya.repository.CustomerRepository;
import com.machine_factotry.kursovaya.repository.InventoryRepository;
import com.machine_factotry.kursovaya.repository.ProductRepository;
import com.machine_factotry.kursovaya.repository.SupplierRepository;
import com.machine_factotry.kursovaya.util.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final CustomerRepository customerRepository;


    public InventoryService(InventoryRepository inventoryRepository,
                            ProductRepository productRepository,
                            SupplierRepository supplierRepository,
                            CustomerRepository customerRepository){
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.customerRepository = customerRepository;
    }

    public List<GoodsMovementDTO> searchMovement(String searchTerm) {
        log.info("Processing movement search for {}...", searchTerm);
        return inventoryRepository.searchInventoryData("%"+searchTerm+"%");
    }

    public int productId(String productName) {
        return productRepository.getProductId(productName);
    }

    public long partnerId(Map<String, String> formData) {
        if (formData.get("movement_type").equals("Поставка")) {
            return supplierRepository.getRandomSupplier();
        }
        return  customerRepository.getRandomCustomer();
    }

    public void addMovement(Map<String, String> formData, long partnerId, int productId) {
        log.info("Adding new movement {}...", formData.get("movement_name"));
        inventoryRepository.addMovement(
                productId,
                partnerId,
                formData.get("movement_type"),
                Integer.parseInt(formData.get("movement_count")),
                LocalDate.parse(formData.get("movement_date")));
    }

    public void deleteMovement(long id) {
        log.info("Deleting movement with id: {}...", id);
        inventoryRepository.deleteMovement(id);
    }

    private GoodsMovementDTO convertToDto(GoodsMovement goodsMovement) {
        String productName = productRepository.getProductName(goodsMovement.getProductId());
        return GoodsMovementDTO.toDTO(goodsMovement, productName);
    }

    public List<GoodsMovementDTO> getInventoryData() {
        log.info("Getting movement data...");
        return DtoConverter.convert(
                inventoryRepository.getAllInventoryData(),
                this::convertToDto
        );
    }
}
