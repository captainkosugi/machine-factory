package com.machine_factotry.kursovaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;


@SpringBootApplication
public class KursovayaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(KursovayaApplication.class, args);

		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
		String sql = "select * from employee";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map.Entry<String, Object> r : rows.get(0).entrySet()) {
			System.out.println(r.getKey() + ": " + r.getValue());
		}

	}

}
