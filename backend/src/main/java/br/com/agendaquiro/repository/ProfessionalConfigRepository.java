package br.com.agendaquiro.repository;


import br.com.agendaquiro.domain.professsional.ProfessionalConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessionalConfigRepository extends JpaRepository<ProfessionalConfig, Long> {


}
