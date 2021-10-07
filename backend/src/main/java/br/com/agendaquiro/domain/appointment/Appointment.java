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
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(name = "professional_id")
    private ProfessionalService professionalService;

    @OneToOne
    private Customer customer;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private BigDecimal amountPaid;
    private String observation;
}
