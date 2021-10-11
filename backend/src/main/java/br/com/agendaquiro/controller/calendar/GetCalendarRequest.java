package br.com.agendaquiro.controller.calendar;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class GetCalendarRequest {
    private Long professionalServiceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
