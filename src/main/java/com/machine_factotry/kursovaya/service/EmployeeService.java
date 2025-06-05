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

    private static final String ADD_EMPLOYEES_DATA =
            """
            insert into employees (employee_name, employee_position, department_it, salary, hire_date)
            values(?, ?, ?, ?::integer, to_date(?, 'YYYY-MM-DD'))
            """;

    private static final String SEARCH_EMPLOYEE =
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
            where e.employee_name ilike ?
            order by ddate desc
            """;

    public List<Map<String, Object>> searchEmployee(String searchTerm) {
        return jdbcTemplate.queryForList(SEARCH_EMPLOYEE, "%"+searchTerm+"%");
    }

    public List<Map<String, Object>> getEmployeesData() {
        return jdbcTemplate.queryForList(GET_EMPLOYEES_DATA_QUERY);
    }

    public void addEmployee(Map<String, String> formData, int departmentId) {
        jdbcTemplate.update(ADD_EMPLOYEES_DATA,
            formData.get("em_name"),
            formData.get("em_position"),
            departmentId,
            formData.get("em_salary"),
            formData.get("em_hire_date")
        );
    }


}
