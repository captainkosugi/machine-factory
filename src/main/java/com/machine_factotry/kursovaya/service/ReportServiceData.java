package com.machine_factotry.kursovaya.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceData {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_REPORT_DATE =
            """
            select to_char(now(), 'DD.MM.YYYY')
            """;

    private static final String GET_PLAN_PRODUCTION_VOLUME =
            """
            select round(count(p.product_name), 0) * 50 from products p
            """;

    private static final String GET_PLAN_MARKETABLE_PRODUCTS =
            """
            select
            round(sum(p.product_price)/sum(p.quantity) * (select count(p.product_name) * 50 from products p)/ 1000000, 2)
            from products p
            """;

    private static final String GET_FACT_PRODUCTION_VOLUME =
            """
            select round(sum(quantity), 0) from products
            """;

    private static final String GET_MARKETABLE_PRODUCTS =
            """
            select round(sum(product_price)/1000000, 2) from products
            """;

    private static final String GET_LABOR_PERFORMANCE =
            """
            select round((select sum(quantity) from products)*1.0/count(*), 2)
            from employees e
            where e.employee_position in ('Инженер', 'Кладовщик')
            """;

    private static final String NOMENCLATURE_PLAN =
            """
            select
             	product_name as p_name,
            	quantity as p_quantity,
            	round((case
                	when quantity > 0
                        then quantity/0.5
                    else 0
                end), 0) as percent
            from products
            order by p_quantity desc
            """;

    private static final String SALARY_SUM =
            """
            select
                d.department_name as d_name,
                round(sum(e.salary), 0) as salary_sum
            from employees e
            left join departments d on d.id = e.department_it
            group by d.department_name
            """;

    private static final String EQUIPMENT_STATUS_REPORT =
            """
            select
                e.quipment_name as eq_name,
                e.equipment_status as eq_status,
                d.department_name as d_name
            from equipment e
            left join departments d on d.id = e.department_it
            order by e.equipment_status
            """;

    private static final String DELIVERY_REPORT =
            """
            select
                p.product_name as p_name,
                s.supplier_name as s_name,
                gm.movement_type mov_type,
                round(gm.quantity, 0) as quan,
                round((gm.quantity * p.product_price), 0) as price
            from goods_movement gm
            left join suppliers s on s.supplier_id = gm.partner_id
            left join products p on p.product_id = gm.product_id
            where movement_type = 'Поставка'
            """;

    private static final String SALES_REPORT =
            """
            select
                p.product_name as p_name,
                c.customer_name as c_name,
                gm.movement_type as mov_type,
                round(gm.quantity, 0) as quan,
                round((gm.quantity * p.product_price), 0) as price
            from goods_movement gm
            left join customers c  on c.customer_id  = gm.partner_id
            left join products p on p.product_id = gm.product_id
            where movement_type = 'Реализация'
            """;

    public List<Map<String, Object>> getSalesReport() {
        return jdbcTemplate.queryForList(SALES_REPORT);
    }

    public List<Map<String, Object>> getDeliveryReport() {
        return jdbcTemplate.queryForList(DELIVERY_REPORT);
    }

    public List<Map<String, Object>> getEquipmentStatusReport() {
        return jdbcTemplate.queryForList(EQUIPMENT_STATUS_REPORT);
    }

    public List<Map<String, Object>> getSalaryDepartmentSum() {
        return jdbcTemplate.queryForList(SALARY_SUM);
    }

    public List<Map<String, Object>> getNomenclaturePlan() {
        return jdbcTemplate.queryForList(NOMENCLATURE_PLAN);
    }

    public String reportDate() {
        return jdbcTemplate.queryForObject(GET_REPORT_DATE, String.class);
    }

    public double planProductionVolume() {
        return jdbcTemplate.queryForObject(GET_PLAN_PRODUCTION_VOLUME, Double.class);
    }

    public double planMarketableProducts() {
        return jdbcTemplate.queryForObject(GET_PLAN_MARKETABLE_PRODUCTS, Double.class);
    }

    public String planProductionVolumeComplete() {
        double percent = planProductionVolume() / 100;
        return new DecimalFormat("#0.0").format(productionVolume()/percent) + "%";
    }

    public String planMarketableProductsComplete() {
       double percent = planMarketableProducts() / 100;
       return new DecimalFormat("#0.0").format(marketableProducts()/percent) + "%";
    }

    public String planLaborPerformance() {
        double percent = 4.8 / 100;
        return new DecimalFormat("#0.0").format(laborPerformance()/percent) + "%";
    }

    public double productionVolume() {
        return jdbcTemplate.queryForObject(GET_FACT_PRODUCTION_VOLUME, Double.class);
    }

    public double marketableProducts() {
        return jdbcTemplate.queryForObject(GET_MARKETABLE_PRODUCTS, Double.class);
    }

    public double laborPerformance() {
        return jdbcTemplate.queryForObject(GET_LABOR_PERFORMANCE, Double.class);
    }
 }
