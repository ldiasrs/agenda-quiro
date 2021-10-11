package br.com.agendaquiro.domain.appointment;

import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository professionalAgendaConfigRepository;

    public AppointmentService(AppointmentRepository professionalAgendaConfigRepository) {
        this.professionalAgendaConfigRepository = professionalAgendaConfigRepository;
    }

    public List<Appointment> getAppointments(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        return professionalAgendaConfigRepository.findByProfessionalServiceAndStartTimeAndEndTime(professionalService, startDate, endDate);
    }
}
