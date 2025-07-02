package com.machine_factotry.kursovaya.repository;

import com.machine_factotry.kursovaya.entity.Equipment;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

    @Query("""
            select
                e.equipment_id as id,
                e.quipment_name,
                e.equipment_type,
                e.department_it,
                e.equipment_status,
                e.installation_date
            from equipment e
            order by e.installation_date desc
            """)
    List<Equipment> getEquipmentData();

    @Query("""
            select
                e.equipment_id as id,
                e.quipment_name,
                e.equipment_type,
                e.department_it,
                e.equipment_status,
                e.installation_date
            from equipment e
            where e.quipment_name ilike :searchTerm
            order by e.installation_date desc
            """)
    List<Equipment> searchEquipment(@Param("searchTerm") String searchTerm);

    @Query("select distinct(e.equipment_status) as equipment_status from equipment e")
    List<String> getEquipmentStatus();

    @Modifying
    @Query("""
            insert into equipment (quipment_name, equipment_type, department_it, equipment_status, installation_date)
            values(:equipment_name, :equipmentType, :department_it, :equipment_status, :installation_date)
            """)
    void addEquipment(
            @Param("equipment_name") String equipmentName,
            @Param("equipmentType") String equipmentType,
            @Param("department_it") int departmentId,
            @Param("equipment_status") String equipmentStatus,
            @Param("installation_date")LocalDate installationDate);

    @Modifying
    @Query("delete from equipment where equipment_id = :equipmentId")
    void deleteEquipment(@Param("equipmentId") long equipmentId);
}
