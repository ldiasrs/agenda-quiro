package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.TestDataBuilder;
import br.com.agendaquiro.domain.calendar.PeriodSlot;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfigRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FreeAppointmentSlotsServiceTest {

    private ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository;
    private FreeAppointmentSlotsService freeAppointmentSlotsService;
    private FreeAppointmentsSlotsGenerator freeAppointmentsSlotsGenerator;

    @Before
    public void mockDependecies() {
        professionalBlockTimeConfigRepository =  mock(ProfessionalBlockTimeConfigRepository.class);
        freeAppointmentsSlotsGenerator =  mock(FreeAppointmentsSlotsGenerator.class);
        freeAppointmentSlotsService = new FreeAppointmentSlotsService(professionalBlockTimeConfigRepository, freeAppointmentsSlotsGenerator);
    }

    @Test
    public void shouldReturnRightFreeAppointmentsSlots() {
        //GIVEN an Professional Service
        TestDataBuilder testDataBuilder = new TestDataBuilder();
        ProfessionalService professionalService = testDataBuilder
                .professionalQuiro().getProfessionalService();
        //AND star and end period
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.minusMinutes(120);
        //AND a timeblock config
        ProfessionalBlockTimeConfig timeConfig = new ProfessionalBlockTimeConfig();
        timeConfig.setProfessional(professionalService.getProfessional());
        when(professionalBlockTimeConfigRepository
                .findByProfessionalId(professionalService.getProfessional()))
                .thenReturn(timeConfig);
        //AND FreeAppointmentsSlots generation
        FreeAppointmentsSlots freeSlots = new FreeAppointmentsSlots();
        freeSlots.addPeriodSlot(PeriodSlot.builder()
                .startTime(startDate.toLocalTime())
                .endTime(endDate.toLocalTime())
                .build());
        when(freeAppointmentsSlotsGenerator.generate(
                professionalService.getServiceType().getDurationInMinutes(),
                startDate, endDate,
                timeConfig
        )).thenReturn(freeSlots);
        //WHEN asked to generate FreeAppointmentsSlots
        FreeAppointmentsSlots freeCalendar = freeAppointmentSlotsService.getFreeAppointmentsSlots(
                professionalService.getProfessional(), startDate, endDate);
        //THEN should return right FreeAppointmentsSlots
        assertThat(freeCalendar.getPeriodSlots()).containsAll(freeSlots.getPeriodSlots());
    }

}
