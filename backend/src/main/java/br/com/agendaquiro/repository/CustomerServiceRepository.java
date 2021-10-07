package br.com.agendaquiro.repository;




import br.com.agendaquiro.domain.customer.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {


}
