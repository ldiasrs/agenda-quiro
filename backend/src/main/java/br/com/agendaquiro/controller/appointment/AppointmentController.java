package br.com.agendaquiro.controller.appointment;

import br.com.agendaquiro.controller.BaseController;
import br.com.agendaquiro.controller.MessageHttpResponse;
import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.appointment.AppointmentCreateRequest;
import br.com.agendaquiro.domain.appointment.AppointmentService;
import br.com.agendaquiro.domain.exception.NotFoundException;
import br.com.agendaquiro.domain.user.User;
import br.com.agendaquiro.domain.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Optional;

import static br.com.agendaquiro.config.PathMappings.*;

@RestController
public class AppointmentController extends BaseController {

    private AppointmentService appointmentService;
    private UserRepository userRepository;

    public AppointmentController(AppointmentService appointmentService, UserRepository userRepository) {
        this.appointmentService = appointmentService;
        this.userRepository = userRepository;
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


    @DeleteMapping(value=APPOINTMENTS_DELETE)
    public ResponseEntity<MessageHttpResponse> delete(@PathVariable Long id,
                                                      @RequestParam(value ="userId") Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.orElseThrow(()->
                new NotFoundException("User not found with ID: " + userId));
        this.appointmentService.deleteAppointment(id, user.get());
        return super.response(
                MessageHttpResponse.builder()
                        .message("Deleted with success ID: " + id)
                        .build()
                        .addValue("id", String.valueOf(id)), HttpStatus.OK);
    }

}
