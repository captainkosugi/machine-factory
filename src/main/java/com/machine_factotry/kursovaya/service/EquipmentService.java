package com.machine_factotry.kursovaya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EquipmentService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getEquipmentData() {
        String query = "select \n" +
                "\te.equipment_id as e_id,\n" +
                "\te.quipment_name as e_name,\n" +
                "\te.equipment_type as e_type,\n" +
                "\td.department_name as d_name,\n" +
                "\te.equipment_status as e_status,\n" +
                "\tto_char((e.installation_date)::date, 'DD.MM.YYYY') as i_date\n" +
                "from equipment e \n" +
                "join departments d on d.id = e.department_it\n" +
                "order by i_date desc";
        return jdbcTemplate.queryForList(query);
    }
}
