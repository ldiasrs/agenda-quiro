package br.com.agendaquiro.domain.appointment;

import br.com.agendaquiro.domain.exception.BadRequestException;
import br.com.agendaquiro.domain.exception.NotFoundException;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.customer.CustomerCrudService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalServiceCrudService;
import br.com.agendaquiro.domain.professsional.Professional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private AppointmentRepository professionalAgendaConfigRepository;
    private CustomerCrudService customerCrudService;
    private ProfessionalServiceCrudService professionalServiceCrudService;

    public AppointmentService(AppointmentRepository professionalAgendaConfigRepository, CustomerCrudService customerCrudService, ProfessionalServiceCrudService professionalServiceCrudService) {
        this.professionalAgendaConfigRepository = professionalAgendaConfigRepository;
        this.customerCrudService = customerCrudService;
        this.professionalServiceCrudService = professionalServiceCrudService;
    }

    public Appointment createAppointment(AppointmentCreateRequest request) {
        Optional.ofNullable(request.getDate()).orElseThrow(()->
                new BadRequestException("AppointmentCreateRequest Date cant be null"));
        Optional.ofNullable(request.getStartTime()).orElseThrow(()->
                new BadRequestException("AppointmentCreateRequest StartTime cant be null"));
        Optional.ofNullable(request.getEndTime()).orElseThrow(()->
                new BadRequestException("AppointmentCreateRequest StartTime cant be null"));
        Optional<Customer> customerOptional = customerCrudService.findById(request.getCustomerId());
        customerOptional.orElseThrow(()->
                new NotFoundException("Customer not found with ID: " + request.getCustomerId()));
        Optional<ProfessionalService> professionalServiceOptional =
                professionalServiceCrudService.findById(request.getProfessionalServiceId());
        professionalServiceOptional.orElseThrow(()->
                new NotFoundException("ProfessionalService not found with ID: " + request.getProfessionalServiceId()));
        Appointment appointment = Appointment.builder()
                .customer(customerOptional.get())
                .professionalService(professionalServiceOptional.get())
                .startTime(request.getDate().atTime(request.getStartTime()))
                .endTime(request.getDate().atTime(request.getEndTime()))
                .observation(request.getObservation())
                .amountPaid(request.getAmountPaid())
                .build();
        return professionalAgendaConfigRepository.save(appointment);
    }

    public List<Appointment> getAppointments(Professional professional, LocalDateTime startDate, LocalDateTime endDate) {
        return professionalAgendaConfigRepository.findByProfessionalAndStartTimeAndEndTime(professional, startDate, endDate);
    }
}
