package br.com.agendaquiro;

import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.ServiceType;
import br.com.agendaquiro.domain.customer.Anamnesis;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.customer.CustomerService;
import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlocked;
import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlockedBuilder;
import br.com.agendaquiro.domain.professsional.CustomRangeTimeBlocked;
import br.com.agendaquiro.domain.professsional.Professional;
import br.com.agendaquiro.domain.professsional.ProfessionalAgendaConfig;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ProfessionalConfigRegister {

    @Test
    public void shouldRegisterProfessional() {
        //DADO um profissional
        Professional professional =
                Professional.builder()
                        .name("ze-massagista")
                        .build();

        //DADO uma configuração de dias para esse profissional
        DaysOfWeekBlocked standarConfig = new DaysOfWeekBlockedBuilder()
                .block(DayOfWeek.SATURDAY)
                .block(DayOfWeek.SUNDAY)
                .blockAllDays(LocalTime.of(00, 00), LocalTime.of(9, 00))
                .blockAllDays(LocalTime.of(18, 00), LocalTime.of(23, 59))
                .build();

        //DADO um blockeio de 2 dias
        CustomRangeTimeBlocked twoDaysNotWorking = CustomRangeTimeBlocked
                .builder()
                .startTime(LocalDateTime.of(LocalDate.of(2021, 9, 22), LocalTime.of(0, 0)))
                .endTime(LocalDateTime.of(LocalDate.of(2021, 9, 23), LocalTime.of(0, 0)))
                .build();

        //DADO um blockeio de 2 dias
        CustomRangeTimeBlocked morningNotWorking = CustomRangeTimeBlocked
                .builder()
                .startTime(LocalDateTime.of(LocalDate.of(2021, 9, 24), LocalTime.of(9, 0)))
                .endTime(LocalDateTime.of(LocalDate.of(2021, 9, 24), LocalTime.of(12, 0)))
                .build();

        //DADO uma configuracao de um profissional
        ProfessionalAgendaConfig agendaConfig = ProfessionalAgendaConfig
                .builder()
                .professional(professional)
                .daysOfWeekBlocked(standarConfig)
                .customRangesOfTimeBlocked(List.of(twoDaysNotWorking, morningNotWorking))
                .build();
    }

    @Test
    public void shouldCreateAppointment() {
        ServiceType quiropraxiaService =
                ServiceType.builder()
                        .description("quiropraxia com ventosa")
                        .durationInMinutes(60)
                        .build();

        Professional zeQuiro = Professional.builder().name("Ze").build();

        Customer luana = Customer.builder()
                .email("lu@gmail.com")
                .name("Luana")
                .build();

        Appointment appointment = Appointment.builder()
                .professional(zeQuiro)
                .customer(luana)
                .dateTime(LocalDateTime.of(LocalDate.of(2021, 9, 24), LocalTime.of(14, 0)))
                .serviceType(quiropraxiaService)
                .amountPaid(BigDecimal.ZERO)
                .build();
    }

    @Test
    public void shouldCreateCustomerService() {
        ServiceType quiropraxiaService =
                ServiceType.builder()
                        .description("quiropraxia com ventosa")
                        .durationInMinutes(60)
                        .build();

        Professional zeQuiro = Professional.builder().name("Ze").build();

        Customer luana = Customer.builder()
                .email("lu@gmail.com")
                .name("Luana")
                .build();

        Appointment appointment = Appointment.builder()
                .professional(zeQuiro)
                .customer(luana)
                .dateTime(LocalDateTime.of(LocalDate.of(2021, 9, 24), LocalTime.of(14, 0)))
                .serviceType(quiropraxiaService)
                .amountPaid(BigDecimal.ZERO)
                .build();

        Anamnesis luanaAnamnesis = Anamnesis.builder()
                .description("")
                .tags(List.of("dor na coluna", "dor no joelho"))
                .customer(luana)
                .build();

        CustomerService customerService = CustomerService.builder()
                .appointment(appointment)
                .painComplaint("")
                .performedProcedures("")
                .postServiceProcedures("")
                .build();
    }
}
