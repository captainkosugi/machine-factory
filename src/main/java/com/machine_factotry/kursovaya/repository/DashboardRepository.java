package com.machine_factotry.kursovaya.repository;

import com.machine_factotry.kursovaya.dto.DashboardDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends CrudRepository<DashboardDTO, Integer> {

    @Query("select count(*) from equipment")
    int getCountEquipment();

    @Query("select sum(p.quantity) from products p")
    int getProductsCount();

    @Query("select count(*) + (select count(*) from customers c) from suppliers s")
    int getPartnersCount();
}




