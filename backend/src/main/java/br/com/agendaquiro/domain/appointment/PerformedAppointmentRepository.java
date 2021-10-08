package br.com.agendaquiro.domain.appointment;

import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PerformedAppointmentRepository extends JpaRepository<PerformedAppointment, Long> {

}
