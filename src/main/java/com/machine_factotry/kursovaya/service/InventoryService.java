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


    public List<Map<String, Object>> getInventoryData() {
        List<Map<String, Object>> result =  jdbcTemplate.queryForList(GET_INVENTORY_DATA);
        return result;
    }
}
