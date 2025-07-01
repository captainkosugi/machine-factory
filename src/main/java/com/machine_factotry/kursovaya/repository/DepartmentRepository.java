package com.machine_factotry.kursovaya.repository;


import com.machine_factotry.kursovaya.entity.Department;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository  extends CrudRepository<Department, Long> {

    @Query("select id as id, department_name as department_name from departments")
    List<Department> getAllDepartments();

    @Query("select id from departments where department_name = :department_name")
    Integer getDepartmentIdByName(@Param("department_name") String departmentName);

    @Query("select department_name from departments where id = :departmentId")
    String getDepartmentNameById(@Param("departmentId") int departmentId);

}
