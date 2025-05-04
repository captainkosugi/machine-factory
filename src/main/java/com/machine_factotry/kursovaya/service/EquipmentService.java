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
    private static final String GET_EQUIPMENT_DATA =
            """
            select
                e.equipment_id as e_id,
                e.quipment_name as e_name,
                e.equipment_type as e_type,
                d.department_name as d_name,
                e.equipment_status as e_status,
                to_char((e.installation_date)::date, 'DD.MM.YYYY') as i_date
            from equipment e
            join departments d on d.id = e.department_it
            order by i_date desc
            """;

    private static final String ADD_EQUIPMENT_DATA =
            """
            insert into equipment (quipment_name, equipment_type, department_it, equipment_status, installation_date)
            values(?, ?, ?, ?, to_date(?, 'YYYY-MM-DD'))
            """;


    private static final String GET_EQUIPMENT_STATUS =
            "select distinct(e.equipment_status) as e_status from equipment e ";

    public List<Map<String, Object>> getEquipmentData() {
        return jdbcTemplate.queryForList(GET_EQUIPMENT_DATA);
    }

    public void addEquipment(Map<String, String> formData, int departmentId) {
        jdbcTemplate.update(ADD_EQUIPMENT_DATA,
                formData.get("eq_name"),
                formData.get("eq_type"),
                departmentId,
                formData.get("eq_status"),
                formData.get("installation_date")
                );
    }

    public List<Map<String, Object>> getEquipmentStatusData() {
        return jdbcTemplate.queryForList(GET_EQUIPMENT_STATUS);
    }
}
