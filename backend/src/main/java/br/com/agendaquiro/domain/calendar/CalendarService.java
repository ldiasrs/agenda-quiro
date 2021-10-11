package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentSlotsService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentsSlots;
import br.com.agendaquiro.domain.freeappointmentsslots.PeriodSlot;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalendarService {

    private FreeAppointmentSlotsService freeAppointmentSlotsService;
    private MergeAppointmentsOnFreeSlotsService mergeAppointmentsOnFreeSlotsService;
    private AppointmentService appointmentService;

    public CalendarService(AppointmentService appointmentService,
                           FreeAppointmentSlotsService freeAppointmentSlotsService,
                           MergeAppointmentsOnFreeSlotsService mergeAppointmentsOnFreeSlotsService) {
        this.appointmentService = appointmentService;
        this.freeAppointmentSlotsService = freeAppointmentSlotsService;
        this.mergeAppointmentsOnFreeSlotsService = mergeAppointmentsOnFreeSlotsService;
    }

    public Calendar getProfessionalCalendarByRange(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        List<Appointment> appointments = appointmentService.getAppointments(professionalService, startDate, endDate);
        FreeAppointmentsSlots freeSlots = freeAppointmentSlotsService.getFreeAppointmentsSlots(professionalService, startDate, endDate);
        List<PeriodSlot> slots = mergeAppointmentsOnFreeSlotsService.merge(PeriodSlot.from(appointments), freeSlots.getPeriodSlots());
        Calendar calendar = new Calendar(professionalService, startDate, endDate);
        calendar.addAll(slots);
        return calendar;
    }
}
