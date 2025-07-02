package com.machine_factotry.kursovaya.repository;

import com.machine_factotry.kursovaya.entity.Product;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(
            """
            select
                p.product_id,
                p.product_name,
                p.product_category,
                p.product_price,
                p.quantity,
                p.product_status
            from products p
            order by p.product_price desc
            """
    )
    List<Product> getProductsData();


    @Query(
            """
            select
                p.product_id,
                p.product_name,
                p.product_category,
                p.product_price,
                p.quantity,
                p.product_status
            from products p
            where p.product_name ilike :searchTerm
            order by p.product_price desc
            """
    )
    List<Product> searchProducts(@Param("searchTerm") String searchTerm);

    @Query("select distinct(p.product_category) from products p")
    List<String> getProductCategory();

    @Query("select distinct(p.product_status) from products p")
    List<String> getProductStatus();

    @Modifying
    @Query("""
        update goods_movement gm
        set processed = true
        where gm.processed = false
        """)
    void setProcessed();

    @Modifying
    @Query("""
            insert into products (product_name, product_category, product_price, quantity)
            values (:productName, :productCategory, :productPrice, :quantity)
            """)
    void addProductsData(
            @Param("productName") String productName,
            @Param("productCategory") String productCategory,
            @Param("productPrice") int productPrice,
            @Param("quantity") int quantity
    );

    @Modifying
    @Query("""
            update products p
            set quantity = p.quantity  + (
                select coalesce(
                    sum
                        (case
                            when gm.movement_type = 'Поставка' then gm.quantity
                            when gm.movement_type = 'Реализация' then -gm.quantity
                            else 0
                        end), 0)
                from goods_movement gm
                where gm.product_id = p.product_id and gm.processed = false
                );
            """)
    void updateProductsData();

    @Modifying
    @Query("delete from products where product_id = :productId")
    void deleteProduct(@Param("productId") long productId);

    // Queries for goods movement("/inventory")
    @Query("select p.product_name from products p where p.product_id = :productId")
    String getProductName(@Param("productId") int productId);

    @Query("select product_id from products where product_name = :productName")
    int getProductId(@Param("productName") String productName);
}
