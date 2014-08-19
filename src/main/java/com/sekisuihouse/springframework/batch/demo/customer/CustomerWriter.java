package com.sekisuihouse.springframework.batch.demo.customer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CustomerWriter extends JdbcDaoSupport implements
		ItemWriter<Customer> {

	private static final String INSERT_SQL = "insert into customer (customer_id, last_name, first_name, mail_address) values (?, ?, ?, ?)";

	public void write(List<? extends Customer> items) throws Exception {
		for (Customer customer : items) {
			getJdbcTemplate().update(
					INSERT_SQL,
					new Object[] { customer.getCustomerId(),
							customer.getLastName(), customer.getFirstName(),
							customer.getMailAddress() });
		}
	}

}
