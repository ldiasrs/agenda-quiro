package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentRepository;
import br.com.agendaquiro.domain.appointment.AppointmentService;
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

    private FreeAppointmentSlotsService freeAppointmentSlotsService;
    private PeriodSlotMergeService periodSlotMergeService;
    private AppointmentService appointmentService;

    public CalendarService(AppointmentService appointmentService,
                           FreeAppointmentSlotsService freeAppointmentSlotsService,
                           PeriodSlotMergeService periodSlotMergeService) {
        this.appointmentService = appointmentService;
        this.freeAppointmentSlotsService = freeAppointmentSlotsService;
        this.periodSlotMergeService = periodSlotMergeService;
    }

    public Calendar getAppointmentCalendar(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        List<Appointment> appointments = appointmentService.getAppointments(professionalService, startDate, endDate);
        FreeAppointmentsSlots freeSlots = freeAppointmentSlotsService.getFreeAppointmentsSlots(professionalService, startDate, endDate);
        List<PeriodSlot> slots = periodSlotMergeService.mergeAppointmentsOnFreeSlots(PeriodSlot.from(appointments), freeSlots.getPeriodSlots());
        Calendar calendar = new Calendar(professionalService, startDate, endDate);
        calendar.addAll(slots);
        return calendar;
    }
}
