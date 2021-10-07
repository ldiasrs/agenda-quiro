package br.com.agendaquiro.domain.customer;



import br.com.agendaquiro.domain.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Page<Customer> findByName(String name, Pageable pageable);
}
