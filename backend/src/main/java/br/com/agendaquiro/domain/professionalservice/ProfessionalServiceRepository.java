package br.com.agendaquiro.domain.professionalservice;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessionalServiceRepository extends CrudRepository<ProfessionalService, Long> {

}
