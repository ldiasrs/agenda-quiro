package br.com.agendaquiro.repository;


import br.com.agendaquiro.domain.professsional.ProfessionalAgendaConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessionalAgendaConfigRepository extends JpaRepository<ProfessionalAgendaConfig, Long> {


}
