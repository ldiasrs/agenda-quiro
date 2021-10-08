package br.com.agendaquiro.domain.professsional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessionalRepository extends CrudRepository<Professional, Long> {

}
