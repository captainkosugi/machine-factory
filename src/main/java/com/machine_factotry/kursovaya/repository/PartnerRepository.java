package com.machine_factotry.kursovaya.repository;

import com.machine_factotry.kursovaya.dto.PartnerDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends CrudRepository<PartnerDTO, Long> {

    @Query(
            """
            select
                row_number() over (order by partner_type, id) as unique_id,
                partner_name,
                partner_type,
                phone,
                email
            from (select
                s.supplier_id as id,
                s.supplier_name as partner_name,
                'Поставщик' as partner_type,
                s.phone as phone,
                s.email as email
            from suppliers s
                union all
            select
                c.customer_id as id,
                c.customer_name as partner_name,
                'Покупатель' as partner_type,
                c.phone as phone,
                c.email as email
            from customers c) as combined_partners
            """
    )
    List<PartnerDTO> getAllPartners();

    @Query(
            """
            select
                row_number() over (order by partner_type, id) as unique_id,
                partner_name,
                partner_type,
                phone,
                email
            from (select
                s.supplier_id as id,
                s.supplier_name as partner_name,
                'Поставщик' as partner_type,
                s.phone as phone,
                s.email as email
            from suppliers s
                union all
            select
                c.customer_id as id,
                c.customer_name as partner_name,
                'Покупатель' as partner_type,
                c.phone as phone,
                c.email as email
            from customers c) as combined_partners
            where partner_name ilike :searchTerm
            """
    )
    List<PartnerDTO> searchPartner(@Param("searchTerm") String searchTerm);
}
