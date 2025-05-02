package com.machine_factotry.kursovaya.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PartnersService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getPartnersData() {
        String query = "select \n" +
                "\trow_number() over (order by partner_type, id) as unique_id,\n" +
                "\tpartner_name,\n" +
                "\tpartner_type,\n" +
                "\tphone,\n" +
                "\temail\n" +
                "from (select \n" +
                "\ts.supplier_id as id,\n" +
                "\ts.supplier_name as partner_name,\n" +
                "\t'Поставщик' as partner_type,\n" +
                "\ts.phone as phone,\n" +
                "\ts.email as email\n" +
                "from suppliers s \n" +
                "union all\n" +
                "select \n" +
                "\tc.customer_id as id,\n" +
                "\tc.customer_name as partner_name,\n" +
                "\t'Покупатель' as partner_type,\n" +
                "\tc.phone as phone,\n" +
                "\tc.email as email\n" +
                "from customers c) as combined_partners;";
        return jdbcTemplate.queryForList(query);
    }
}
