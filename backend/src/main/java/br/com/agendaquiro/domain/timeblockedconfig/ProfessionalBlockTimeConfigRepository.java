package br.com.agendaquiro.domain.timeblockedconfig;

import br.com.agendaquiro.domain.professsional.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalBlockTimeConfigRepository extends JpaRepository<ProfessionalBlockTimeConfig, Long> {

    @Query("FROM ProfessionalBlockTimeConfig p " +
            "WHERE p.professional= :professional ")
    ProfessionalBlockTimeConfig findByProfessionalId(Professional professional);
}
