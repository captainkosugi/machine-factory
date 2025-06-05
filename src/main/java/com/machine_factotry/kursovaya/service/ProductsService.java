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

    private static final String UPDATE_PRODUCTS_DATA =
            """
            update products p
            set quantity = p.quantity  + (
                select coalesce(
                    sum
                        (case
                            when gm.movement_type = 'Поставка' then gm.quantity
                            when gm.movement_type = 'Реализация' then -gm.quantity
                            else 0
                        end), 0)
                from goods_movement gm
                where gm.product_id = p.product_id and gm.processed = false
                );
            """;

    private static final String SET_PROCESSED =
            """
            update goods_movement gm
            set processed = true
            where gm.processed = false
            """;

    private static final String GET_PRODUCTS_DATA_QUERY =
            """
            select 
                p.product_id as p_id,
                p.product_name as p_name,
                p.product_category as p_category,
                p.product_price as p_price,
                p.quantity as p_quantity,
                p.product_status as p_status
            from products p
            order by p_price desc
            """;

    private static final String GET_PRODUCTS_CATEGORY =
            """
            select distinct(p.product_category) as category_name from products p;         
            """;

    private static final String GET_PRODUCT_STATUS =
            """
            select distinct(p.product_status) as product_status from products p
            """;

    private static final String ADD_PRODUCTS_DATA =
            """
            insert into products (product_name, product_category, product_price, quantity)
            values (?, ?, ?::integer, ?::integer)
            """;

    private static final String SEARCH_PRODUCTS =
            """
            select 
                p.product_id as p_id,
                p.product_name as p_name,
                p.product_category as p_category,
                p.product_price as p_price,
                p.quantity as p_quantity,
                p.product_status as p_status
            from products p
            where p.product_name ilike ?
            order by p_price desc
            """;

    public List<Map<String, Object>> searchProducts(String searchTerm) {
        return jdbcTemplate.queryForList(SEARCH_PRODUCTS, "%"+searchTerm+"%");
    }

    public void updateProductsData() {
        jdbcTemplate.execute(UPDATE_PRODUCTS_DATA);
    }

    public List<Map<String, Object>> getProductsCategory() {
        return jdbcTemplate.queryForList(GET_PRODUCTS_CATEGORY);
    }

    public List<Map<String, Object>> getProductStatus() {
        return jdbcTemplate.queryForList(GET_PRODUCT_STATUS);
    }

    public List<Map<String, Object>> getProductsData() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(GET_PRODUCTS_DATA_QUERY);
        updateProductsData();
        jdbcTemplate.execute(SET_PROCESSED);
        return result;
    }

    public void addProduct(Map<String, String> formData) {
        jdbcTemplate.update(ADD_PRODUCTS_DATA,
            formData.get("p_name"),
            formData.get("p_category"),
            formData.get("p_price"),
            formData.get("p_count")
        );
    }
}
