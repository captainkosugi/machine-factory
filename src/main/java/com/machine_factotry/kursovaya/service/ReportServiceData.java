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
            select count(p.product_name) * 50 from products p\s
            """;

    private static final String GET_PLAN_MARKETABLE_PRODUCTS =
            """
            select
            round(sum(p.product_price)/sum(p.quantity) * (select count(p.product_name) * 50 from products p)/ 1000000, 2)
            from products p
            """;

    private static final String GET_FACT_PRODUCTION_VOLUME =
            """
            select sum(quantity) from products
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
