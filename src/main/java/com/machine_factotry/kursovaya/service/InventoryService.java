package com.machine_factotry.kursovaya.service;

import com.machine_factotry.kursovaya.dto.GoodsMovementDTO;
import com.machine_factotry.kursovaya.entity.GoodsMovement;
import com.machine_factotry.kursovaya.repository.CustomerRepository;
import com.machine_factotry.kursovaya.repository.InventoryRepository;
import com.machine_factotry.kursovaya.repository.ProductRepository;
import com.machine_factotry.kursovaya.repository.SupplierRepository;
import com.machine_factotry.kursovaya.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    @Autowired
    JdbcTemplate jdbcTemplate;

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
        inventoryRepository.addMovement(
                productId,
                partnerId,
                formData.get("movement_type"),
                Integer.parseInt(formData.get("movement_count")),
                LocalDate.parse(formData.get("movement_date")));
    }

    private GoodsMovementDTO convertToDto(GoodsMovement goodsMovement) {
        String productName = productRepository.getProductName(goodsMovement.getProductId());
        return GoodsMovementDTO.toDTO(goodsMovement, productName);
    }

    public List<GoodsMovementDTO> getInventoryData() {
        return DtoConverter.convert(
                inventoryRepository.getAllInventoryData(),
                this::convertToDto
        );
    }
}
