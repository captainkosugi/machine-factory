package com.machine_factotry.kursovaya.repository;

import com.machine_factotry.kursovaya.entity.Supplier;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    @Query("select supplier_id  from suppliers order by random() limit 1")
    long getRandomSupplier();

    @Modifying
    @Query("""
            insert into suppliers(supplier_name, phone, email)
            values(:supplierName, :phone, :email)
            """)
    void addSupplier(
            @Param("supplierName") String supplierName,
            @Param("phone") String phone,
            @Param("email") String email
    );

    @Modifying
    @Query("""
            delete from suppliers where supplier_id =
            (select supplier_id from suppliers where supplier_name  = :supplierName)
            """)
    void deleteSupplier(@Param("supplierName") String supplierName);

}
