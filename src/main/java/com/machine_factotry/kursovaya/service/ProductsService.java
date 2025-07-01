package com.machine_factotry.kursovaya.service;


import com.machine_factotry.kursovaya.dto.ProductDTO;
import com.machine_factotry.kursovaya.entity.Product;
import com.machine_factotry.kursovaya.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductsService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> searchProducts(String searchTerm) {
        Iterable<Product> productIterable =
                productRepository.searchProducts("%" + searchTerm + "%");
        List<ProductDTO> productList = new ArrayList<>();
        for (Product product : productIterable) {
            ProductDTO dto = ProductDTO.toDTO(product);
            productList.add(dto);
        }
        return productList;
    }

    public void updateProductsData() {
        productRepository.updateProductsData();
    }

    public List<String> getProductsCategory() {
        return productRepository.getProductCategory();
    }

    public List<String> getProductStatus() {
        return productRepository.getProductStatus();
    }

    public List<ProductDTO> getProductsData() {
        Iterable<Product> productIterable = productRepository.getProductsData();
        List<ProductDTO> productList = new ArrayList<>();
        for (Product product : productIterable) {
            ProductDTO dto = ProductDTO.toDTO(product);
            productList.add(dto);
        }
        updateProductsData();
        productRepository.setProcessed();
        return productList;
    }

    public void addProduct(Map<String, String> formData) {
        productRepository.addProductsData(
            formData.get("p_name"),
            formData.get("p_category"),
            Integer.parseInt(formData.get("p_price")),
            Integer.parseInt(formData.get("p_count"))
        );
    }
}
