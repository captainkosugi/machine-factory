package com.machine_factotry.kursovaya.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductsService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getProductsData() {
        String query = "select \n" +
                "\tp.product_id as p_id,\n" +
                "\tp.product_name as p_name,\n" +
                "\tp.product_category as p_category,\n" +
                "\tp.product_price as p_price,\n" +
                "\tp.quantity as p_quantity,\n" +
                "\tp.product_status as p_status\n" +
                "from products p\n" +
                "order by p_price desc";
        return jdbcTemplate.queryForList(query);
    }
}
