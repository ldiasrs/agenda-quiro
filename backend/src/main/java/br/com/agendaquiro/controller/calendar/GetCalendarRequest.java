package br.com.agendaquiro.controller.calendar;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class GetCalendarRequest {
    private LocalDate startTime;
    private LocalDate endTime;
}
