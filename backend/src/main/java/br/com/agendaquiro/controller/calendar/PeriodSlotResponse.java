package br.com.agendaquiro.controller.calendar;

import br.com.agendaquiro.domain.calendar.SlotStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeriodSlotResponse {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private SlotStatus status;
}
