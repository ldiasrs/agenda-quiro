package br.com.agendaquiro.common.utils;


import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.to.CustomerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service
@Scope("singleton")
@Transactional(readOnly = true)
public class DataTransferObjectUtil {

	@Autowired
	private CopyUtil copyUtil;

	public Customer getFillCustomer(final CustomerTO customerTO) throws ParseException {
		Customer customer = null;
		if (customerTO != null) {
			customer = new Customer();
			copyUtil.copy(customerTO, customer);
		}
		return customer;
	}

	public CustomerTO getFillCustomerTO(final Customer customer) {
		final CustomerTO customerTO = new CustomerTO();
		copyUtil.copy(customer, customerTO);

		return customerTO;
	}

	public List<CustomerTO> getFillCustomerTOsWithoutList(final List<Customer> customers) {
		final List<CustomerTO> customerTOs = new ArrayList<CustomerTO>();
		for (final Customer customer : customers) {
			customerTOs.add(getFillCustomerTOWithoutList(customer));
		}
		return customerTOs;
	}

	public CustomerTO getFillCustomerTOWithoutList(final Customer customer) {
		final CustomerTO customerTO = new CustomerTO();
		copyUtil.copy(customer, customerTO);
		return customerTO;
	}
}
