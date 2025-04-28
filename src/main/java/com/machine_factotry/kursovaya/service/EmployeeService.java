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

    public List<Map<String, Object>> getEmployeesData() {
        String query = "select \n" +
                "\te.employee_id as id, \n" +
                "\te.employee_name as name, \n" +
                "\te.employee_position as position, \n" +
                "\td.department_name as dp_name,\n" +
                "\te.salary as salary, \n" +
                "\tto_char((e.hire_date)::date, 'DD.MM.YYYY') as ddate\n" +
                "from employees e\n" +
                "join departments d on d.id = e.department_it\n" +
                "order by ddate desc";
        return jdbcTemplate.queryForList(query);
    }


}
