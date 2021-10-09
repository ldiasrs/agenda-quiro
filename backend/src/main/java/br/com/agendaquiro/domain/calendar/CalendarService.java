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
    private PeriodSlotMergeService periodSlotMergeService;

    public CalendarService(AppointmentRepository professionalAgendaConfigRepository,
                           ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository,
                           FreeAppointmentSlotsService freeAppointmentSlotsService,
                           PeriodSlotMergeService periodSlotMergeService) {
        this.professionalAgendaConfigRepository = professionalAgendaConfigRepository;
        this.professionalBlockTimeConfigRepository = professionalBlockTimeConfigRepository;
        this.freeAppointmentSlotsService = freeAppointmentSlotsService;
        this.periodSlotMergeService = periodSlotMergeService;
    }

    public AppointmentCalendar getAppointmentCalendar(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        List<Appointment> appointments = getAppointments(professionalService, startDate, endDate);
        FreeAppointmentsSlots freeSlots = getFreeAppointmentsSlots(professionalService, startDate, endDate);
        List<PeriodSlot> slots = periodSlotMergeService.merge(PeriodSlot.from(appointments), freeSlots.getPeriodSlots());
        AppointmentCalendar calendar = new AppointmentCalendar(professionalService, startDate, endDate);
        calendar.addAll(slots);
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
