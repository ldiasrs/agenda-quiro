package br.com.agendaquiro.domain.agendanotblocked;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@ToString
public class PeriodSlot {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
