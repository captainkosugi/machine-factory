package com.machine_factotry.kursovaya.repository;

import com.machine_factotry.kursovaya.entity.Customer;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select customer_id from customers order by random() limit 1")
    long getRandomCustomer();

    @Modifying
    @Query("""
            insert into customers(customer_name, phone, email)
            values(:customerName, :phone, :email)
            """)
    void addCustomer(
            @Param("customerName") String customerName,
            @Param("phone") String phone,
            @Param("email") String email
    );

    @Modifying
    @Query("""
           delete from customers where customer_id =
           (select customer_id from customers where customer_name = :customerName)
           """)
    void deleteCustomer(@Param("customerName") String customerName);
}
