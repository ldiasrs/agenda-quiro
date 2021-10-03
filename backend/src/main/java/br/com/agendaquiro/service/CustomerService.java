package br.com.agendaquiro.service;

import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.to.CustomerTO;
import br.com.agendaquiro.domain.to.HttpResponseTO;
import br.com.agendaquiro.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public HttpResponseTO edit(Customer customer) {
		try {
			Customer customerBD = this.customerRepository.save(customer);

			return HttpResponseTO.success("result", customerBD);
		} catch (Exception e) {
			return HttpResponseTO.fail(e.getMessage());
		}
	}

	public HttpResponseTO delete(Long id) {
		try {
			this.customerRepository.deleteById(id);

			return HttpResponseTO.success("result", id);
		} catch (Exception e) {
			return HttpResponseTO.fail(e.getMessage());
		}
	}

	public Page<Customer> findByFilter(final String name, final Pageable pageable) {

		return this.customerRepository.findByName(name, pageable);
	}



}