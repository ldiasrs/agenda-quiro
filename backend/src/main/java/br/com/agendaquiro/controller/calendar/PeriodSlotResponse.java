package br.com.agendaquiro.controller.calendar;

import br.com.agendaquiro.domain.calendar.SlotStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PeriodSlotResponse {
    private Long appointmentId;
    private SlotStatus status;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private String observation;
    private String clientName;
    private String clientPhone;
    private String serviceName;
    private BigDecimal amountPaid;

}
