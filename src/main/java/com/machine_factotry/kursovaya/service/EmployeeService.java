package com.machine_factotry.kursovaya.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_EMPLOYEES_DATA_QUERY =
            """
            select
                e.employee_id as id,
                e.employee_name as name,
                e.employee_position as position,
                d.department_name as dp_name,
                e.salary as salary,
                to_char((e.hire_date)::date, 'DD.MM.YYYY') as ddate
            from employees e
            join departments d on d.id = e.department_it
            order by ddate desc
            """;

    public List<Map<String, Object>> getEmployeesData() {
        return jdbcTemplate.queryForList(GET_EMPLOYEES_DATA_QUERY);
    }


}
