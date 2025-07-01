package com.machine_factotry.kursovaya.repository;

import com.machine_factotry.kursovaya.dto.GoodsMovementDTO;
import com.machine_factotry.kursovaya.entity.GoodsMovement;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository extends CrudRepository<GoodsMovement, Long> {

    @Query("""
            select
                gm.movement_id,
                gm.product_id,
                gm.movement_type,
                gm.movement_date,
                gm.quantity
            from goods_movement gm
            order by gm.movement_date desc
            """)
    List<GoodsMovement> getAllInventoryData();

    @Query("""
         select
            gm.movement_id,
            p.product_name as product_name,
            gm.movement_type,
            to_char(gm.movement_date::date, 'DD.MM.YYYY') as movement_date,
            gm.quantity
         from goods_movement gm
         join products p on p.product_id = gm.product_id
         where product_name ilike :searchTerm
         order by gm.movement_date desc
          """)
    List<GoodsMovementDTO> searchInventoryData(@Param("searchTerm") String searchTerm);

    @Modifying
    @Query("""
    insert into goods_movement(product_id, partner_id, movement_type, quantity, movement_date)
    values(:productId, :partnerId, :movementType, :quantity, :movementDate)""")
    void addMovement(
            @Param("productId") int productId,
            @Param("partnerId") long partnerId,
            @Param("movementType") String movementType,
            @Param("quantity") int quantity,
            @Param("movementDate") LocalDate movementDate
            );
}
