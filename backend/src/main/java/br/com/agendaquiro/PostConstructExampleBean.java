package br.com.agendaquiro;

import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentRepository;
import br.com.agendaquiro.domain.appointment.PerformedAppointment;
import br.com.agendaquiro.domain.appointment.PerformedAppointmentRepository;
import br.com.agendaquiro.domain.customer.Anamnesis;
import br.com.agendaquiro.domain.customer.AnamnesisRepository;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.customer.CustomerRepository;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalServiceRepository;
import br.com.agendaquiro.domain.professsional.*;
import br.com.agendaquiro.domain.servicetype.ServiceType;
import br.com.agendaquiro.domain.servicetype.ServiceTypeRepository;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfigRepository;
import br.com.agendaquiro.domain.timeblockedconfig.TimeBlockedConfigBuilder;
import br.com.agendaquiro.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

@Component
public class PostConstructExampleBean {

    @Autowired
    private ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository;
    @Autowired
    private ProfessionalServiceRepository professionalServiceRepository;
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private ProfessionalRepository professionalRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PerformedAppointmentRepository performedAppointmentRepository;
    @Autowired
    private AnamnesisRepository anamnesisRepository;

    private final Logger LOG =
            Logger.getLogger(PostConstructExampleBean.class.getName());
    @PostConstruct
    public void init() {
        //USER
        User admin = User.builder()
                .username("admin@admin.com")
                .password("")
                .build();

        //SERVICE
        ServiceType quiropraxiaService =
                ServiceType.builder()
                        .description("quiropraxia com ventosa")
                        .durationInMinutes(60)
                        .build();
        serviceTypeRepository.save(quiropraxiaService);

        //PROFISSIONAL
        Professional aline = Professional.builder().name("Aline Silva").build();
        professionalRepository.save(aline);

        //PROFISSIONAL + SERVICE
        ProfessionalService quiroAline = new ProfessionalService(aline, quiropraxiaService);
        professionalServiceRepository.save(quiroAline);

        //TIME BLOCKED (PROFISSIONAL + SERVICE)
        ProfessionalBlockTimeConfig timeBlockedAlineQuiro = new TimeBlockedConfigBuilder(quiroAline)
                .block(DayOfWeek.SATURDAY)
                .block(DayOfWeek.SUNDAY)
                .blockAllDays(LocalTime.of(00, 00), LocalTime.of(9, 00))
                .blockAllDays(LocalTime.of(18, 00), LocalTime.of(23, 59))
                .build();
        professionalBlockTimeConfigRepository.save(timeBlockedAlineQuiro);

        //CLIENTE
        Customer luana = Customer.builder()
                .email("lu@gmail.com")
                .birthDate(LocalDate.now())
                .cpf("00876726252")
                .gender("F")
                .weight("165")
                .height("72")
                .name("Luana")
                .phone("51-887652725")
                .build();
        customerRepository.save(luana);

        //APPOINTMENT
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMinutes(quiropraxiaService.getDurationInMinutes());
        Appointment appointmentLuana = Appointment.builder()
                .professionalService(quiroAline)
                .customer(luana)
                .startTime(start)
                .endTime(end)
                .amountPaid(BigDecimal.ZERO)
                .observation("Paga na hora")
                .build();
        appointmentRepository.save(appointmentLuana);


        //ANAMNESIS
        Anamnesis luanaAnamnesis = Anamnesis.builder()
                .description("Anamnesis da cliente")
                .tags(List.of("dor na coluna", "dor no joelho"))
                .customer(luana)
                .build();
        anamnesisRepository.save(luanaAnamnesis);

        //PERFORMED REGISTER
        PerformedAppointment perfomendAppointment = PerformedAppointment.builder()
                .appointment(appointmentLuana)
                .observations("")
                .postServiceProcedures("")
                .build();
        performedAppointmentRepository.save(perfomendAppointment);
    }
}
