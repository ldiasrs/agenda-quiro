package br.com.agendaquiro.domain.timeblockedconfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalBlockTimeConfigRepository extends JpaRepository<ProfessionalBlockTimeConfig, Long> {

    ProfessionalBlockTimeConfig findByProfessionalId();
}
