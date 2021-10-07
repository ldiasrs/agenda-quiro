package br.com.agendaquiro.domain.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CustomerCrudService {

	private CustomerRepository customerRepository;

	public CustomerCrudService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer add(Customer customer) {
		return this.customerRepository.save(customer);
	}

	public Customer edit(Customer customer) {
		return this.customerRepository.save(customer);
	}

	public void delete(Long id) {
		this.customerRepository.deleteById(id);
	}

	public Page<Customer> findByFilter(final String name, final Pageable pageable) {
		return this.customerRepository.findByName(name, pageable);
	}
}
