package br.com.agendaquiro;

import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.PerformedAppointment;
import br.com.agendaquiro.domain.customer.Anamnesis;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.professsional.Professional;
import br.com.agendaquiro.domain.servicetype.ServiceType;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import br.com.agendaquiro.domain.timeblockedconfig.TimeBlockedConfigBuilder;
import br.com.agendaquiro.domain.user.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
public class TestDataBuilder {

    private ProfessionalService professionalService;
    private Professional professional;
    private ServiceType serviceType;
    private User user;
    private ProfessionalBlockTimeConfig timeBlocked;
    private Customer custome;
    private Appointment appointment;
    private PerformedAppointment perfomendAppointment;
    private Anamnesis anamnesis;

    public TestDataBuilder professionalQuiro() {
        buildProfessionalService(
                buildProfessional().getProfessional(),
                buildServiceType().getServiceType());
        return this;
    }

    public TestDataBuilder buildUser() {
        //USER
        user = User.builder()
                .username("admin@admin.com")
                .password("")
                .build();
        return this;
    }

    public TestDataBuilder buildServiceType() {
        //SERVICE
        serviceType =
                ServiceType.builder()
                        .description("quiropraxia com ventosa")
                        .durationInMinutes(60)
                        .build();
        return this;
    }

    public TestDataBuilder buildProfessional() {
        //PROFISSIONAL
        professional = Professional.builder().name("Aline Silva").build();
        return this;
    }

    public TestDataBuilder buildProfessionalBlockTimeConfig(ProfessionalService professional) {
        //TIME BLOCKED (PROFISSIONAL + SERVICE)
        timeBlocked = new TimeBlockedConfigBuilder(professional)
                .block(DayOfWeek.SATURDAY)
                .block(DayOfWeek.SUNDAY)
                .blockAllDays(LocalTime.of(00, 00), LocalTime.of(9, 00))
                .blockAllDays(LocalTime.of(18, 00), LocalTime.of(23, 59))
                .build();
        return this;
    }

    public TestDataBuilder buildCustomer() {
        //CLIENTE
        custome = Customer.builder()
                .email("lu@gmail.com")
                .birthDate(LocalDate.now())
                .cpf("00876726252")
                .gender("F")
                .weight("165")
                .height("72")
                .name("Luana")
                .phone("51-887652725")
                .build();
        return this;
    }

    public TestDataBuilder buildAppointment(ProfessionalService quiroAline, Customer luana) {
        //APPOINTMENT
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMinutes(quiroAline.getServiceType().getDurationInMinutes());
         appointment = Appointment.builder()
                .professionalService(quiroAline)
                .customer(luana)
                .startTime(start)
                .endTime(end)
                .amountPaid(BigDecimal.ZERO)
                .observation("Paga na hora")
                .build();
        return this;
    }

    public TestDataBuilder buildPerformedAppointment(Appointment appointmentLuana) {
        //PERFORMED REGISTER
         perfomendAppointment = PerformedAppointment.builder()
                .appointment(appointmentLuana)
                .observations("")
                .postServiceProcedures("")
                .build();
        return this;
    }

    public TestDataBuilder buildAnamnesis(Customer customer) {
        //ANAMNESIS
         anamnesis = Anamnesis.builder()
                .description("Anamnesis da cliente")
                .tags(List.of("dor na coluna", "dor no joelho"))
                .customer(customer)
                .build();
        return this;
    }

    public TestDataBuilder buildProfessionalService(Professional professional, ServiceType serviceType) {
        //PROFISSIONAL + SERVICE
        professionalService = new ProfessionalService(professional, serviceType);
        return this;
    }
}
