package br.com.agendaquiro.repository;


import br.com.agendaquiro.domain.professsional.CustomRangeTimeBlocked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomRangeTimeBlockedRepository extends JpaRepository<CustomRangeTimeBlocked, Long> {


}
