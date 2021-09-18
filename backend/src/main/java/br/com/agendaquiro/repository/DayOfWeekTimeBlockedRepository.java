package br.com.agendaquiro.repository;

import br.com.agendaquiro.domain.daysofweekblocked.DayOfWeekTimeBlocked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOfWeekTimeBlockedRepository extends JpaRepository<DayOfWeekTimeBlocked, Integer> {

}
