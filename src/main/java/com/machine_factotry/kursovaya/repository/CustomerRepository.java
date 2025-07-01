package com.machine_factotry.kursovaya.repository;

import com.machine_factotry.kursovaya.entity.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select customer_id from customers order by random() limit 1")
    long getRandomCustomer();

}
