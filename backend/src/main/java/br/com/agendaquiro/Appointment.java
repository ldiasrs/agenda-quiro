package br.com.agendaquiro;

import br.com.agendaquiro.domain.professsional.Professional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {
    private Professional professional;
    private Customer customer;
    private LocalDateTime dateTime;
    private ServiceType serviceType;
    private BigDecimal amountPaid;
    private String observation;
}
