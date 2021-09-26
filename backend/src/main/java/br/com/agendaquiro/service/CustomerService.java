package br.com.agendaquiro.service;

import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.to.HttpResponseTO;
import br.com.agendaquiro.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public HttpResponseTO add(Customer customer) {
		try {
			Customer customerBD = this.customerRepository.save(customer);

			return HttpResponseTO.success("result", customerBD);
		} catch (Exception e) {
			return HttpResponseTO.fail(e.getMessage());
		}
	}



}