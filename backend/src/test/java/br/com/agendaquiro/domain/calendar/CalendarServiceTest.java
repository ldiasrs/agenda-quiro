package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.TestDataBuilder;
import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentSlotsService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentsSlots;
import br.com.agendaquiro.domain.freeappointmentsslots.PeriodSlot;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfigRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalendarServiceTest {

    private ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository;
    private CalendarService calendarService;
    private FreeAppointmentSlotsService freeAppointmentSlotsService;
    private MergeAppointmentsOnFreeSlotsService mergeAppointmentsOnFreeSlotsService;
    private AppointmentService appointmentService;

    @Before
    public void mockDependecies() {
        professionalBlockTimeConfigRepository =  mock(ProfessionalBlockTimeConfigRepository.class);
        appointmentService =  mock(AppointmentService.class);
        freeAppointmentSlotsService =  mock(FreeAppointmentSlotsService.class);
        mergeAppointmentsOnFreeSlotsService =  mock(MergeAppointmentsOnFreeSlotsService.class);
        calendarService = new CalendarService(
                appointmentService,
                freeAppointmentSlotsService,
                mergeAppointmentsOnFreeSlotsService);
    }

    @Test
    public void shouldReturnCalendarAppointmentsSlots() {
        //GIVEN a professional Service
        TestDataBuilder testDataBuilder = new TestDataBuilder();
        ProfessionalService professionalService = testDataBuilder
                .professionalQuiro().getProfessionalService();
        //AND a period
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.minusMinutes(120);
        //AND a freeSlots
        FreeAppointmentsSlots freeAppointmentsSlots = new FreeAppointmentsSlots();
        freeAppointmentsSlots.addPeriodSlot(PeriodSlot.builder().build());
        when(freeAppointmentSlotsService.getFreeAppointmentsSlots(professionalService, startDate, endDate))
                .thenReturn(freeAppointmentsSlots);
        //AND appointment
        Appointment appointment = testDataBuilder
                .buildAppointment(
                        professionalService, testDataBuilder.buildCustomer().getCustome())
                .getAppointment();
        List<Appointment> appointments = Arrays.asList(appointment);
        when(appointmentService
                .getAppointments(professionalService, startDate, endDate))
                .thenReturn(appointments);
        //AND a merged slots
        List<PeriodSlot> mergedSlots = new ArrayList<>();
        List<PeriodSlot> from = PeriodSlot.from(appointments);
        mergedSlots.addAll(from);
        mergedSlots.addAll(freeAppointmentsSlots.getPeriodSlots());
        when(mergeAppointmentsOnFreeSlotsService.merge
                (ArgumentMatchers.anyList(), ArgumentMatchers.anyList()))
                .thenReturn(mergedSlots);
        //WHEN ASKED for calendar
        Calendar calendar = calendarService.getProfessionalCalendarByRange(professionalService, startDate, endDate);
        //THEN
        assertThat(calendar.getPeriodSlots()).containsAll(mergedSlots);
    }
}
