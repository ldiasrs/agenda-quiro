package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentSlotsService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentsSlots;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.professsional.Professional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
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

    public Calendar getProfessionalCalendarByRange(Professional professional, LocalDateTime startDate, LocalDateTime endDate) {
        List<Appointment> appointments = appointmentService.getAppointments(professional, startDate, endDate);
        FreeAppointmentsSlots freeSlots = freeAppointmentSlotsService.getFreeAppointmentsSlots(
                professional,
                LocalDateTime.now().withMinute(0).withSecond(0).withNano(0),
                endDate.withMinute(0).withSecond(0).withNano(0));
        List<PeriodSlot> slots = mergeAppointmentsOnFreeSlotsService.merge(
                freeSlots.getPeriodSlots(), PeriodSlot.from(appointments));
        Collections.sort(slots, (sa, sb) -> LocalDateTime.of(sa.getDate(), sa.getStartTime())
                .compareTo(
                        LocalDateTime.of(sb.getDate(), sb.getStartTime())));
        Calendar calendar = new Calendar(professional, startDate, endDate);
        calendar.addAll(slots);
        return calendar;
    }
}
