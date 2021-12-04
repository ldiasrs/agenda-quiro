package br.com.agendaquiro.controller.appointment;

import br.com.agendaquiro.controller.BaseController;
import br.com.agendaquiro.controller.MessageHttpResponse;
import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentCreateRequest;
import br.com.agendaquiro.domain.appointment.AppointmentService;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.customer.CustomerCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

import static br.com.agendaquiro.config.PathMappings.*;
import static br.com.agendaquiro.controller.customer.request.CustomerRequest.convertToEntity;

@RestController
public class AppointmentController extends BaseController {

    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping(value=APPOINTMENTS)
    public ResponseEntity<MessageHttpResponse> add(@Valid @RequestBody AppointmentCreateRequest appointmentCreateRequest) throws ParseException {
        Appointment appointment = appointmentService.createAppointment(appointmentCreateRequest);
        return super.response(
                MessageHttpResponse
                        .builder()
                        .message("Appointment created created ID: " + appointment.getId())
                        .build()
                        .addValue("id", String.valueOf(appointment.getId())),
                HttpStatus.CREATED);
    }

}
