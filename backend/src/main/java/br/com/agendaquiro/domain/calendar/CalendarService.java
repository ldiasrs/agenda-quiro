package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentSlotsService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentsSlots;
import br.com.agendaquiro.domain.professsional.Professional;
import br.com.agendaquiro.domain.user.User;
import br.com.agendaquiro.domain.user.UserProfessional;
import br.com.agendaquiro.domain.user.UserProfessionalRepository;
import br.com.agendaquiro.domain.user.UserRepository;
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
    private UserProfessionalRepository userProfessionalRepository;

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
        List<PeriodSlot> mergedSlots = mergeAppointmentsOnFreeSlotsService.merge(
                freeSlots.getPeriodSlots(), PeriodSlot.from(appointments));
        sortByDate(mergedSlots);
        return new Calendar(professional, startDate, endDate, mergedSlots);
    }

    private void sortByDate(List<PeriodSlot> slots) {
        Collections.sort(slots, Comparator.comparing(sa -> LocalDateTime.of(sa.getDate(), sa.getStartTime())));
    }

    public Calendar getProfessionalCalendarOfUserByRange(User user, LocalDateTime start, LocalDateTime end) {
        UserProfessional professionalUser = userProfessionalRepository.findByUser(user);
        return getProfessionalCalendarByRange(professionalUser.getProfessional(), start,end);
    }
}
