package com.sekisuihouse.springframework.batch.demo.customer;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {

	@Override
	public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
		Customer customer = new Customer();
		customer.setCustomerId(fieldSet.readString("customer_id"));
		customer.setLastName(fieldSet.readString("last_name"));
		customer.setFirstName(fieldSet.readString("first_name"));
		customer.setMailAddress(fieldSet.readString("mail_address"));

		return customer;
	}

}
