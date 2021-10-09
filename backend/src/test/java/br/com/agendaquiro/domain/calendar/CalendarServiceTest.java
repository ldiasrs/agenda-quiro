package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.TestDataBuilder;
import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentRepository;
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

    private AppointmentRepository professionalAgendaConfigRepository;
    private ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository;
    private CalendarService calendarService;
    private FreeAppointmentSlotsService freeAppointmentSlotsService;
    private PeriodSlotMergeService periodSlotMergeService;

    @Before
    public void mockDependecies() {
        professionalAgendaConfigRepository = mock(AppointmentRepository.class);
        professionalBlockTimeConfigRepository =  mock(ProfessionalBlockTimeConfigRepository.class);
        freeAppointmentSlotsService =  mock(FreeAppointmentSlotsService.class);
        periodSlotMergeService =  mock(PeriodSlotMergeService.class);
        calendarService = new CalendarService(
                professionalAgendaConfigRepository,
                professionalBlockTimeConfigRepository,
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

    @Test
    public void shouldReturnFreeAppointmentsSlots() {
        //GIVEN
        TestDataBuilder testDataBuilder = new TestDataBuilder();
        ProfessionalService professionalService = testDataBuilder
                .professionalQuiro().getProfessionalService();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.minusMinutes(120);
        FreeAppointmentsSlots freeSlots = new FreeAppointmentsSlots();
        freeSlots.addPeriodSlot(PeriodSlot.builder()
                .startTime(startDate.toLocalTime())
                .endTime(endDate.toLocalTime())
                .build());
        ProfessionalBlockTimeConfig timeConfig = new ProfessionalBlockTimeConfig();
        timeConfig.setProfessionalService(professionalService);
        when(professionalBlockTimeConfigRepository
                .findByProfessionalServiceId(professionalService.getId()))
                .thenReturn(timeConfig);
        when(freeAppointmentSlotsService.generateFreeAppointmentSlots(
                professionalService.getServiceType().getDurationInMinutes(),
                startDate, endDate,
                timeConfig
        )).thenReturn(freeSlots);
        //WHEN
        FreeAppointmentsSlots freeCalendar = calendarService.getFreeAppointmentsSlots(
                professionalService, startDate, endDate);
        //THEN
        assertThat(freeCalendar.getPeriodSlots()).containsAll(freeSlots.getPeriodSlots());
    }

    @Test
    public void shouldReturnAppointmentCalendar() {
        //GIVEN
        TestDataBuilder testDataBuilder = new TestDataBuilder();
        ProfessionalService professionalService = testDataBuilder
                .professionalQuiro().getProfessionalService();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.minusMinutes(120);
        Appointment apointment = testDataBuilder
                .buildAppointment(
                        professionalService, testDataBuilder.buildCustomer().getCustome()).getAppointment();
        when(professionalAgendaConfigRepository
                .findByProfessionalServiceAndStartTimeAndEndTime(
                        professionalService, startDate, endDate) ).thenReturn(Arrays.asList(apointment));
        //WHEN
        List<Appointment> apointments = calendarService.getAppointments(professionalService, startDate, endDate);
        //THEN
        assertThat(apointments).contains(apointment);
    }
}
