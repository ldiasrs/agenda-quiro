package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentsSlotsBuilder;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentsSlots;
import br.com.agendaquiro.domain.freeappointmentsslots.PeriodSlot;
import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.appointment.AppointmentRepository;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfigRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalendarService {

    private AppointmentRepository professionalAgendaConfigRepository;
    private ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository;

    public CalendarService(AppointmentRepository professionalAgendaConfigRepository, ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository) {
        this.professionalAgendaConfigRepository = professionalAgendaConfigRepository;
        this.professionalBlockTimeConfigRepository = professionalBlockTimeConfigRepository;
    }

    public AppointmentCalendar getAppointmentCalendar(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        AppointmentCalendar calendar = new AppointmentCalendar(professionalService, startDate, endDate);
        List<Appointment> appointments = getAppointments(professionalService, startDate, endDate);
        for (Appointment appointment : appointments) {
            calendar.add(PeriodSlot.builder()
                    .startTime(appointment.getStartTime().toLocalTime())
                    .endTime(appointment.getEndTime().toLocalTime())
                    .date(appointment.getStartTime().toLocalDate())
                    .build());
        }
        FreeAppointmentsSlots freeSlots = getFreeAppointmentsSlots(professionalService, startDate, endDate);
        calendar.addAll(freeSlots.getPeriodSlots());
        return calendar;
    }

    public FreeAppointmentsSlots getFreeAppointmentsSlots(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        ProfessionalBlockTimeConfig timeBlockedConfig = professionalBlockTimeConfigRepository.findByProfessionalServiceId(1L);
        FreeAppointmentsSlots freeAppointments = new FreeAppointmentsSlotsBuilder()
                .daysOfWeekBlocked(timeBlockedConfig)
                .period(
                        professionalService.getServiceType().getDurationInMinutes(),
                        startDate, endDate
                ).build();
        return freeAppointments;
    }

    public List<Appointment> getAppointments(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        return professionalAgendaConfigRepository.findByProfessionalServiceAndStartTimeAndEndTime(professionalService, startDate, endDate);
    }
}
