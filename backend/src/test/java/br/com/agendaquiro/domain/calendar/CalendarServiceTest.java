package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.TestDataBuilder;
import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentRepository;
import br.com.agendaquiro.domain.appointment.AppointmentService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentSlotsService;
import br.com.agendaquiro.domain.freeappointmentsslots.FreeAppointmentsSlots;
import br.com.agendaquiro.domain.freeappointmentsslots.PeriodSlot;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfigRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalendarServiceTest {

    private ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository;
    private CalendarService calendarService;
    private FreeAppointmentSlotsService freeAppointmentSlotsService;
    private PeriodSlotMergeService periodSlotMergeService;
    private AppointmentService appointmentService;

    @Before
    public void mockDependecies() {
        professionalBlockTimeConfigRepository =  mock(ProfessionalBlockTimeConfigRepository.class);
        appointmentService =  mock(AppointmentService.class);
        freeAppointmentSlotsService =  mock(FreeAppointmentSlotsService.class);
        periodSlotMergeService =  mock(PeriodSlotMergeService.class);
        calendarService = new CalendarService(
                appointmentService,
                freeAppointmentSlotsService,
                periodSlotMergeService);
    }

    @Test
    public void shouldReturnCalendarAppointmentsSlots() {
        TestDataBuilder testDataBuilder = new TestDataBuilder();
        ProfessionalService professionalService = testDataBuilder
                .professionalQuiro().getProfessionalService();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.minusMinutes(120);
        calendarService.getAppointmentCalendar(professionalService, startDate, endDate);
    }



}
