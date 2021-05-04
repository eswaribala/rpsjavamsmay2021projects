package com.boa.upload.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boa.upload.models.Customer;
import com.boa.upload.services.CustomerService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class CustomerGQLQueries implements GraphQLQueryResolver {
	@Autowired
	private CustomerService customerService;
	
	
	public List<Customer> findAllCustomers() {
		
		return this.customerService.getAllCustomers();
	}
	
	public Customer findCustomer(long customerId) {
		return this.customerService.getCustomerById(customerId);
	}

}
