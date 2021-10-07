package br.com.agendaquiro.repository;

import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlocked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaysOfWeekBlockedRepository extends JpaRepository<DaysOfWeekBlocked, Long> {

}
