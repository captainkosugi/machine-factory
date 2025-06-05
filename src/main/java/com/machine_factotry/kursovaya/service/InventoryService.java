package com.machine_factotry.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_INVENTORY_DATA =
    """
    select 
        gm.movement_id as id,
        p.product_name as p_name,
        gm.movement_type as gm_type,
        to_char((gm.movement_date)::date, 'DD.MM.YYYY') as move_date,
        (gm.quantity)::integer as move_quantity
    from goods_movement gm
    join products p on p.product_id = gm.product_id
    """;

    private static final String ADD_INVENTORY_DATA =
    """
    insert into goods_movement(product_id, partner_id, movement_type, quantity, movement_date)
    values(?, ?, ?, ?::integer, ?::date)
    """;

    private static final String GET_RANDOM_SUPPLIER =
    """
    select s.supplier_id  from suppliers s order by random() limit 1
    """;

    private static final String GET_RANDOM_CUSTOMER =
    """
    select c.customer_id from customers c order by random() limit 1
    """;

    private static final String GET_PRODUCT_ID_BY_NAME =
    """
    select product_id from products where product_name = ?
    """;

    private static final String SEARCH_MOVEMENT =
            """
            select 
                gm.movement_id as id,
                p.product_name as p_name,
                gm.movement_type as gm_type,
                to_char((gm.movement_date)::date, 'DD.MM.YYYY') as move_date,
                (gm.quantity)::integer as move_quantity
            from goods_movement gm
            join products p on p.product_id = gm.product_id
            where product_name ilike ?
            """;

    public List<Map<String, Object>> searchMovement(String searchTerm) {
        return jdbcTemplate.queryForList(SEARCH_MOVEMENT, "%"+searchTerm+"%");
    }

    public int productId(String productName) {
        return jdbcTemplate.queryForObject(GET_PRODUCT_ID_BY_NAME, Integer.class, productName);
    }

    public int partnerId(Map<String, String> formData) {
        if (formData.get("movement_type").equals("Поставка")) {
            return jdbcTemplate.queryForObject(GET_RANDOM_SUPPLIER, Integer.class);
        }
        return jdbcTemplate.queryForObject(GET_RANDOM_CUSTOMER, Integer.class);
    }

    public void addMovement(Map<String, String> formData, int partnerId, int productId) {
        jdbcTemplate.update(ADD_INVENTORY_DATA,
                productId,
                partnerId,
                formData.get("movement_type"),
                formData.get("movement_count"),
                formData.get("movement_date"));
    }

    public List<Map<String, Object>> getInventoryData() {
        return jdbcTemplate.queryForList(GET_INVENTORY_DATA);
    }
}
