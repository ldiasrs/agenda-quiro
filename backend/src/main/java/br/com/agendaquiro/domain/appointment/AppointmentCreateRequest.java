package br.com.agendaquiro.domain.appointment;

import br.com.agendaquiro.domain.appointment.Appointment;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentCreateRequest implements Serializable {

    private Long professionalServiceId;
    private Long customerId;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private BigDecimal amountPaid;
    private String observation;
}
