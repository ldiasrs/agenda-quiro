package br.com.agendaquiro;

import br.com.agendaquiro.domain.User;
import br.com.agendaquiro.domain.appointment.ServiceType;
import br.com.agendaquiro.domain.customer.Anamnesis;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.customer.CustomerService;
import br.com.agendaquiro.domain.daysofweekblocked.DayOfWeekTimeBlocked;
import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlocked;
import br.com.agendaquiro.domain.professsional.CustomRangeTimeBlocked;
import br.com.agendaquiro.domain.professsional.Professional;
import br.com.agendaquiro.domain.professsional.ProfessionalAgendaConfig;
import br.com.agendaquiro.domain.professsional.ProfessionalConfig;
import br.com.agendaquiro.repository.*;
import br.com.agendaquiro.repository.converter.ListDayOfWeekToIntegerConverter;
import br.com.agendaquiro.repository.converter.ListTagStringToStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class PostConstructExampleBean {
    @Autowired
    private DayOfWeekTimeBlockedRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnamnesisRepository anamnesisRepository;

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private ProfessionalConfigRepository professionalConfigRepository;

    @Autowired
    private CustomRangeTimeBlockedRepository customRangeTimeBlockedRepository;

    @Autowired
    private ProfessionalAgendaConfigRepository professionalAgendaConfigRepository;

    @Autowired
    private DaysOfWeekBlockedRepository daysOfWeekBlockedRepository;

    private final Logger LOG = Logger.getLogger(PostConstructExampleBean.class.getName());

    /*@PostConstruct
    public void init() {
        DayOfWeekTimeBlocked saved = repository.save(DayOfWeekTimeBlocked.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(1))
                .build());
        Optional<DayOfWeekTimeBlocked> fromdb = repository.findById(saved.getId());
        LOG.info(fromdb.get().toString());
    }

    @PostConstruct
    public void customer() {
        Customer saved = customerRepository.save(Customer.builder()
                .name("Customer PostConstruct")
                .email("customer@email.com")
                .phone("99999999")
                .cpf("11111111111")
                .birthDate(LocalDate.now())
                .height("100")
                .weight("50")
                .gender("M")
                .build());
        Optional<Customer> fromdb = customerRepository.findById(saved.getId());
        LOG.info("Customer PostConstruct - OK" + fromdb.get().toString());
    }

    @PostConstruct
    public void user() {
        User saved = userRepository.save(User.builder()
                .tenant("User PostConstruct")
                .username("user@email.com")
                .enabled(true)
                .password("99999999")
                .build());
        Optional<User> fromdb = userRepository.findById(saved.getId());
        LOG.info("User PostConstruct - OK" + fromdb.get().toString());
    }

    @PostConstruct
    public void anamnesis() {
        List<String> tags = new ArrayList<String>();
        tags.add("Coluna");
        tags.add("Lombar");

        Anamnesis saved = anamnesisRepository.save(Anamnesis.builder()
                .description("Anamnesis PostConstruct")
                .customer(Customer.builder().id(1L).build())
                .tags(tags)
                .build());
        Optional<Anamnesis> fromdb = anamnesisRepository.findById(saved.getId());
        LOG.info("Anamnesis PostConstruct - OK" + fromdb.get().toString());
    }

   @PostConstruct
    public void customerService() {
        CustomerService saved = customerServiceRepository.save(CustomerService.builder()
                .appointmentId(1L)
                .painComplaint("Dor na coluna")
                .performedProcedures("Alinhamento da lombar")
                .postServiceProcedures("Alongamentos")
                .build());
        Optional<CustomerService> fromdb = customerServiceRepository.findById(saved.getId());
        LOG.info("CustomerService PostConstruct - OK" + fromdb.get().toString());
    }

    @PostConstruct
    public void serviceType() {
        ServiceType saved = serviceTypeRepository.save(ServiceType.builder()
                .description("Quiropraxia")
                .durationInMinutes(60)
                .build());
        Optional<ServiceType> fromdb = serviceTypeRepository.findById(saved.getId());
        LOG.info("ServiceType PostConstruct - OK" + fromdb.get().toString());
    }

    @PostConstruct
    public void professional() {
        Professional saved = professionalRepository.save(Professional.builder()
                .name("Pablo Quiropraxista")
                .build());
        Optional<Professional> fromdb = professionalRepository.findById(saved.getId());
        LOG.info("Professional PostConstruct - OK" + fromdb.get().toString());
    }*/

    /*@PostConstruct
    public void daysOfWeekBlocked() {
        List<DayOfWeek> dayOfWeekList = new ArrayList<DayOfWeek>();
        dayOfWeekList.add(DayOfWeek.FRIDAY);
        dayOfWeekList.add(DayOfWeek.SATURDAY);

        List<DayOfWeekTimeBlocked> dayOfWeekTimeBlockedList = new ArrayList<DayOfWeekTimeBlocked>();
        dayOfWeekTimeBlockedList.add(DayOfWeekTimeBlocked.builder().id(1L).build());

        DaysOfWeekBlocked saved = daysOfWeekBlockedRepository.save(DaysOfWeekBlocked.builder()
                .wholeDaysOfWeekBlocked(dayOfWeekList)
                .periodOfTimeDayWeekBlocked(dayOfWeekTimeBlockedList)
                .professionalAgendaConfig(ProfessionalAgendaConfig.builder().id(1L).build())
                .build());
        Optional<DaysOfWeekBlocked> fromdb = daysOfWeekBlockedRepository.findById(saved.getId());
        LOG.info("DaysOfWeekBlocked PostConstruct - OK" + fromdb.get().toString());
    }

    /*@PostConstruct
    public void professionalConfig() {
        ProfessionalConfig saved = professionalConfigRepository.save(ProfessionalConfig.builder()
                .professional(Professional.builder().id(1L).build())
                .daysOfWeekBlocked(DaysOfWeekBlocked.builder().id(1L).build())
                .build());
        Optional<ProfessionalConfig> fromdb = professionalConfigRepository.findById(saved.getId());
        LOG.info("ProfessionalConfig PostConstruct - OK" + fromdb.get().toString());
    }

    @PostConstruct
    public void professionalAgendaConfig() {
        ProfessionalAgendaConfig saved = professionalAgendaConfigRepository.save(ProfessionalAgendaConfig.builder()
                .professional(Professional.builder().id(1L).build())
                .build());
        Optional<ProfessionalAgendaConfig> fromdb = professionalAgendaConfigRepository.findById(saved.getId());
        LOG.info("ProfessionalAgendaConfig PostConstruct - OK" + fromdb.get().toString());
    }

    @PostConstruct
    public void customRangeTimeBlocked() {
        CustomRangeTimeBlocked saved = customRangeTimeBlockedRepository.save(CustomRangeTimeBlocked.builder()
                .professionalAgendaConfig(ProfessionalAgendaConfig.builder().id(1L).build())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusHours(1))
                .build());
        Optional<CustomRangeTimeBlocked> fromdb = customRangeTimeBlockedRepository.findById(saved.getId());
        LOG.info("CustomRangeTimeBlocked PostConstruct - OK" + fromdb.get().toString());
    }

*/




}
