package br.com.agendaquiro.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformedAppointmentRepository extends JpaRepository<PerformedAppointment, Long> {

}
