package com.sekisuihouse.springframework.batch.demo.customer;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomerBatch {

	public static void main(String[] args) throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring/customerJob.xml",
						"classpath:spring/data-source-context.xml",
						"classpath:spring/simple-job-launcher-context.xml" });

		Job customerJob = appContext.getBean(Job.class);
		JobLauncher jobLauncher = appContext.getBean(JobLauncher.class);

		JobParameters jp = new JobParameters();
		jobLauncher.run(customerJob, jp);

		// check
		DataSource dataSource = appContext.getBean(DataSource.class);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.queryForObject(
				"select count(*) from customer", Integer.class);
		System.out.println("Customer = " + count);
	}

}
