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
import br.com.agendaquiro.domain.user.Authority;
import br.com.agendaquiro.domain.user.AuthorityRepository;
import br.com.agendaquiro.domain.user.User;
import br.com.agendaquiro.domain.user.UserRepository;
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
public class SeedDataBase {

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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    private final Logger LOG =
            Logger.getLogger(SeedDataBase.class.getName());
    @PostConstruct
    public void init() {
        if(userRepository.findAll().iterator().hasNext()) {
            return;
        }
        //USER
        User admin = User.builder()
                .username("admin@quiroapp.com")
                .tenant("quiroappp")
                .enabled(true)
                .password("$2a$12$.nmAVl6I8HeX1i.1pbUdDOQ5Nfc93IWJXR92hHwSgMOZ2bHKNqJqG")
                .build();
        userRepository.save(admin);

        authorityRepository.save(
                Authority.builder()
                        .username(admin.getUsername())
                        .authority("ROLE_ADMIN")
                        .build());

        User user = User.builder()
                .username("user@quiroapp.com")
                .tenant("quiroappp")
                .enabled(true)
                .password("$2a$12$.nmAVl6I8HeX1i.1pbUdDOQ5Nfc93IWJXR92hHwSgMOZ2bHKNqJqG")
                .build();
        userRepository.save(user);
        authorityRepository.save(
                Authority.builder()
                        .username(user.getUsername())
                        .authority("ROLE_USER")
                        .build());

        User manager = User.builder()
                .username("manager@quiroapp.com")
                .tenant("quiroappp")
                .enabled(true)
                .password("$2a$12$.nmAVl6I8HeX1i.1pbUdDOQ5Nfc93IWJXR92hHwSgMOZ2bHKNqJqG")
                .build();
        userRepository.save(manager);
        authorityRepository.save(
                Authority.builder()
                        .username(manager.getUsername())
                        .authority("ROLE_MANAGER")
                        .build());
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
        ProfessionalBlockTimeConfig alineTimeBlocked = new TimeBlockedConfigBuilder(aline)
                .blockAllDays(LocalTime.of(18,0), LocalTime.of(23,59))
                .blockAllDays(LocalTime.of(00,00), LocalTime.of(10,0))
                .blockAllDays(LocalTime.of(12,00), LocalTime.of(13,0))
                .blockSunday()
                .blockSaturday()
                .build();

        professionalBlockTimeConfigRepository.save(alineTimeBlocked);

        //CLIENTE
        Customer luana = Customer.builder()
                .email("lu@gmail.com")
                .birthDate(LocalDate.now())
                .cpf("00876726252")
                .gender("female")
                .weight("165")
                .height("72")
                .name("Luana")
                .phone("51-887652725")
                .build();
        customerRepository.save(luana);

        for (int i = 0; i < 20; i++) {
            //CLIENTE
            Customer gabriel = Customer.builder()
                    .email("gaby"+i+"@gmail.com")
                    .birthDate(LocalDate.now())
                    .cpf("009826257836")
                    .gender("male")
                    .weight("172")
                    .height("76")
                    .name("Gabriel " + i)
                    .phone("54-99762" + i)
                    .build();
            customerRepository.save(gabriel);
        }

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
