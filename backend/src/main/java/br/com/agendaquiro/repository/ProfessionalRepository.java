package br.com.agendaquiro.repository;


import br.com.agendaquiro.domain.professsional.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {


}
