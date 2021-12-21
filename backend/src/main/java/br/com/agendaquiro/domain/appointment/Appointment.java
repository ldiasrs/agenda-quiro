package br.com.agendaquiro.domain.appointment;

import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@ToString
@Table(name = "APPOINTMENT")
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PROFESSIONAL_SERVICE_ID")
    private ProfessionalService professionalService;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private BigDecimal amountPaid;
    private String observation;
}
