package br.com.agendaquiro.domain.appointment;

import br.com.agendaquiro.domain.professsional.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("FROM Appointment a " +
            "WHERE a.professionalService.professional = :professional " +
            "AND a.startTime <> :startTime " +
            "AND a.endTime <> :endTime ")
    List<Appointment> findByProfessionalAndStartTimeAndEndTime(Professional professional, LocalDateTime startTime, LocalDateTime endTime);
}
