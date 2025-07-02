package com.machine_factotry.kursovaya.repository;



import com.machine_factotry.kursovaya.entity.Employee;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {


    // queries for "/employees" endpoint
    @Query("""
           select
                e.employee_id as id,
                e.employee_name as name,
                e.employee_position as position,
                e.department_it,
                e.salary,
                e.hire_date
            from employees e
            order by e.hire_date desc
            """)
    List<Employee> getAllEmployees();

    @Query("""
            select
                e.employee_id as id,
                e.employee_name as name,
                e.employee_position as position,
                e.department_it,
                e.salary,
                e.hire_date
            from employees e
            where e.employee_name ilike :searchTerm
            order by e.hire_date
            """)
    List<Employee> searchEmployees(@Param("searchTerm") String searchTerm);

    @Modifying
    @Query("""
            insert into employees (employee_name, employee_position, department_it, salary, hire_date)
            values(:name, :employeePosition, :department_it, :salary, :ddate)
            """)
    void addNewEmployee(@Param("name") String name,
                        @Param("employeePosition") String employeePosition,
                        @Param("department_it") int departmentIt,
                        @Param("salary") double salary,
                        @Param("ddate") LocalDate ddate);

    @Modifying
    @Query("delete from employees where employee_id = :employeeId")
    void deleteEmployee(@Param("employeeId") long employeeId);


    // query for dashboard
    @Query("select count(*) from employees")
    int getCountEmployees();
}
