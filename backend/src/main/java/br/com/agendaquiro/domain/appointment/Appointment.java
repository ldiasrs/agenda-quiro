package br.com.agendaquiro.domain.appointment;

import br.com.agendaquiro.domain.appointment.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Long professionalId;
    private Long customerId;
    private LocalDateTime dateTime;

    @OneToOne
    @JoinColumn(name = "SERVICE TYPE_ID")
    private ServiceType serviceType;

    private BigDecimal amountPaid;
    private String observation;
}
