package com.machine_factotry.kursovaya.service;


import com.machine_factotry.kursovaya.dto.ProductDTO;
import com.machine_factotry.kursovaya.entity.Product;
import com.machine_factotry.kursovaya.repository.ProductRepository;
import com.machine_factotry.kursovaya.util.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductsService {

    private static final Logger log = LoggerFactory.getLogger(ProductsService.class);
    private final ProductRepository productRepository;

    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductDTO convertToDto(Product product) {
        return ProductDTO.toDTO(product);
    }

    public List<ProductDTO> getProductsData() {
        List<ProductDTO> productList = DtoConverter.convert(
                productRepository.getProductsData(),
                this::convertToDto
        );
        updateProductsData();
        productRepository.setProcessed();
        log.info("Getting products data...");
        return productList;
    }

    public List<ProductDTO> searchProducts(String searchTerm) {
        log.info("Processing search products for {}...", searchTerm);
        return DtoConverter.convert(
                productRepository.searchProducts("%"+searchTerm+"%"),
                this::convertToDto
        );
    }

    public void updateProductsData() {
        log.info("Updating products data...");
        productRepository.updateProductsData();
    }

    public List<String> getProductsCategory() {
        return productRepository.getProductCategory();
    }

    public List<String> getProductStatus() {
        return productRepository.getProductStatus();
    }

    public void addProduct(Map<String, String> formData) {
        log.info("Adding new product {}...", formData.get("p_name"));
        productRepository.addProductsData(
            formData.get("p_name"),
            formData.get("p_category"),
            Integer.parseInt(formData.get("p_price")),
            Integer.parseInt(formData.get("p_count"))
        );
    }

    public void deleteProduct(long id) {
        log.info("Deleting product with id: {}", id);
        productRepository.deleteProduct(id);
    }
}
