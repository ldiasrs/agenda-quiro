package br.com.agendaquiro.domain.appointment;

import br.com.agendaquiro.domain.audit.Audit;
import br.com.agendaquiro.domain.audit.AuditRepository;
import br.com.agendaquiro.domain.exception.BadRequestException;
import br.com.agendaquiro.domain.exception.NotFoundException;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.customer.CustomerCrudService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalServiceCrudService;
import br.com.agendaquiro.domain.professsional.Professional;
import br.com.agendaquiro.domain.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private CustomerCrudService customerCrudService;
    private ProfessionalServiceCrudService professionalServiceCrudService;
    private AuditRepository auditRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, CustomerCrudService customerCrudService, ProfessionalServiceCrudService professionalServiceCrudService, AuditRepository auditRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerCrudService = customerCrudService;
        this.professionalServiceCrudService = professionalServiceCrudService;
        this.auditRepository = auditRepository;
    }

    public void deleteAppointment(Long appointmentId, User user) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        appointment.orElseThrow(()->
                new NotFoundException("Appointment not found with ID: " + appointmentId));
        auditRepository.save(
                Audit.builder()
                .dateTime(LocalDateTime.now())
                .auditDescription(user + "--" + appointment.get())
                .build());
        appointmentRepository.deleteById(appointmentId);
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
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointments(Professional professional, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findByProfessionalAndStartTimeAndEndTime(professional, startDate, endDate);
    }
}
