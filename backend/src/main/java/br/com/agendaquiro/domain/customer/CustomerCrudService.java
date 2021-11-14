package br.com.agendaquiro.domain.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerCrudService {

	private CustomerRepository customerRepository;

	public CustomerCrudService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer add(Customer customer) {
		return this.customerRepository.save(customer);
	}

	public Customer edit(Customer customer, Long id) {
		customer.setId(id);
		return this.customerRepository.save(customer);
	}

	public Optional<Customer> findById(Long id) {
		return this.customerRepository.findById(id);
	}

	public void delete(Long id) {
		this.customerRepository.deleteById(id);
	}

	public Iterable<Customer> findAll() {
		return this.customerRepository.findAll();
	}

	public Page<Customer> findByFilter(String searchTerm, PageRequest pageRequest) {
		return this.customerRepository.search(searchTerm.toLowerCase(), pageRequest);
	}
}
