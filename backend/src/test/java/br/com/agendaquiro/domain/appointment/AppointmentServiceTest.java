package br.com.agendaquiro.domain.appointment;

import br.com.agendaquiro.TestDataBuilder;
import br.com.agendaquiro.domain.audit.AuditRepository;
import br.com.agendaquiro.domain.customer.CustomerCrudService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalServiceCrudService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppointmentServiceTest {
    private AppointmentService appointmentService;
    private AppointmentRepository appointmentRepository;

    @Before
    public void mockDeps() {
        appointmentRepository = mock(AppointmentRepository.class);
        CustomerCrudService customerCrudService = mock(CustomerCrudService.class);
        ProfessionalServiceCrudService professionalServiceCrudService = mock(ProfessionalServiceCrudService.class);
        AuditRepository auditRepository = mock(AuditRepository.class);
        appointmentService = new AppointmentService(appointmentRepository, customerCrudService, professionalServiceCrudService, auditRepository);
    }

    @Test
    public void shouldReturnAppointmentCalendar() {
        //GIVEN an Professional Service
        TestDataBuilder testDataBuilder = new TestDataBuilder();
        ProfessionalService professionalService = testDataBuilder
                .professionalQuiro().getProfessionalService();
        //AND star and end period
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.minusMinutes(120);
        //AND Appointment for the professional on DB
        Appointment appointment = testDataBuilder
                .buildAppointment(
                        professionalService, testDataBuilder.buildCustomer().getCustome()).getAppointment();
        when(appointmentRepository
                .findByProfessionalAndStartTimeAndEndTime(
                        professionalService.getProfessional(), startDate, endDate) ).thenReturn(Arrays.asList(appointment));
        //WHEN asked for appointment
        List<Appointment> appointments = appointmentService.getAppointments(professionalService.getProfessional(), startDate, endDate);
        //THEN should return right appointment
        assertThat(appointments).contains(appointment);
    }

}
