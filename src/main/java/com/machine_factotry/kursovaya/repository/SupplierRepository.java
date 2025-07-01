package com.machine_factotry.kursovaya.repository;

import com.machine_factotry.kursovaya.entity.Supplier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    @Query("select supplier_id  from suppliers order by random() limit 1")
    long getRandomSupplier();

}
