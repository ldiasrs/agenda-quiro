package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentRepository;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentSlotsService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentsSlots;
import br.com.agendaquiro.domain.freeappointmentsslots.PeriodSlot;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfigRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalendarService {

    private AppointmentRepository professionalAgendaConfigRepository;
    private ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository;
    private FreeAppointmentSlotsService freeAppointmentSlotsService;

    public CalendarService(AppointmentRepository professionalAgendaConfigRepository, ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository, FreeAppointmentSlotsService freeAppointmentSlotsService) {
        this.professionalAgendaConfigRepository = professionalAgendaConfigRepository;
        this.professionalBlockTimeConfigRepository = professionalBlockTimeConfigRepository;
        this.freeAppointmentSlotsService = freeAppointmentSlotsService;
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
        ProfessionalBlockTimeConfig timeBlockedConfig =
                professionalBlockTimeConfigRepository.findByProfessionalServiceId(professionalService.getId());
        FreeAppointmentsSlots freeAppointments = freeAppointmentSlotsService
                .generateFreeAppointmentSlots
                        (professionalService.getServiceType().getDurationInMinutes(),
                                startDate, endDate, timeBlockedConfig);
        return freeAppointments;
    }

    public List<Appointment> getAppointments(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        return professionalAgendaConfigRepository.findByProfessionalServiceAndStartTimeAndEndTime(professionalService, startDate, endDate);
    }
}
