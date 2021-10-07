package br.com.agendaquiro.domain.servicetype;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceTypeRepository extends CrudRepository<ServiceType, Long> {

}
