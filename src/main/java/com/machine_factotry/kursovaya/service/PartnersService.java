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

    private static final String GET_ALL_PARTNERS_QUERY =
            """
            select 
                row_number() over (order by partner_type, id) as unique_id,
                partner_name,
                partner_type,
                phone,
                email
            from (select
                s.supplier_id as id,
                s.supplier_name as partner_name,
                'Поставщик' as partner_type,
                s.phone as phone,
                s.email as email
            from suppliers s
                union all
            select 
                c.customer_id as id,
                c.customer_name as partner_name,
                'Покупатель' as partner_type,
                c.phone as phone,
                c.email as email
            from customers c) as combined_partners
            """;

    private static final String ADD_SUPPLIER =
            """
            insert into suppliers (supplier_name, phone, email)
            values (?, ?, ?)
            """;


    private static final String ADD_CUSTOMER =
            """
            insert into customers(customer_name, phone, email)
            values(?, ?, ?)
            """;

    private static final String SEARCH_PARTNER =
            """
            select 
                row_number() over (order by partner_type, id) as unique_id,
                partner_name,
                partner_type,
                phone,
                email
            from (select
                s.supplier_id as id,
                s.supplier_name as partner_name,
                'Поставщик' as partner_type,
                s.phone as phone,
                s.email as email
            from suppliers s
                union all
            select 
                c.customer_id as id,
                c.customer_name as partner_name,
                'Покупатель' as partner_type,
                c.phone as phone,
                c.email as email
            from customers c) as combined_partners
            where partner_name ilike ?
            """;

    public List<Map<String, Object>> searchPartner(String searchTerm) {
        return jdbcTemplate.queryForList(SEARCH_PARTNER, "%"+searchTerm+"%");
    }

    public void addPartner(Map<String, String> formData) {
        if (formData.get("partner_type").equals("Покупатель")) {
            jdbcTemplate.update(ADD_CUSTOMER,
                    formData.get("partner_name"),
                    formData.get("partner_phone"),
                    formData.get("partner_email"));
        }
        jdbcTemplate.update(ADD_SUPPLIER,
                    formData.get("partner_name"),
                    formData.get("partner_phone"),
                    formData.get("partner_email"));
    }

    public List<Map<String, Object>> getPartnersData() {
        return jdbcTemplate.queryForList(GET_ALL_PARTNERS_QUERY);
    }
}
