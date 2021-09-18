package br.com.agendaquiro.domain.calendar;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Builder
@Getter
public class DayOfWeekTimeBlocked {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
