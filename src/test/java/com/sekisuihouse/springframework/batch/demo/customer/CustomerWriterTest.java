package com.sekisuihouse.springframework.batch.demo.customer;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/customerJob.xml" })
public class CustomerWriterTest {

	@Autowired
	private CustomerWriter customerWriter;

	private JdbcTemplate JdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.JdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional
	@Test
	public void testWrite() throws Exception {
		Customer c1 = new Customer();
		c1.setCustomerId("1111");
		c1.setLastName("Tomoya");
		c1.setFirstName("Sasaki");
		c1.setMailAddress("sasaki-tomoya-1@example.com");
		Customer c2 = new Customer();
		c2.setCustomerId("2222");
		c2.setLastName("Tomoya2");
		c2.setFirstName("Sasaki2");
		c2.setMailAddress("sasaki-tomoya-2@example.com");

		List<Customer> items = new ArrayList<>();
		items.add(c1);
		items.add(c2);
		customerWriter.write(items);

		int count = JdbcTemplate.queryForObject(
				"select count(*) from customer", Integer.class);
		assertTrue(count == 2);
	}

}
