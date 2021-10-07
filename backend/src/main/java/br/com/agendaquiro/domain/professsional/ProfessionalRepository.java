package br.com.agendaquiro.domain.professsional;



import br.com.agendaquiro.domain.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessionalRepository extends CrudRepository<Professional, Long> {

}
