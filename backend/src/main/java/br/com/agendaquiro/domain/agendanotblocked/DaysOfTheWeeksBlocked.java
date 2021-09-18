package br.com.agendaquiro.domain.agendanotblocked;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class DaysOfTheWeeksBlocked {

    private Set<DayOfWeek> wholeDaysOfWeekBlocked;
    private Set<DayOfWeekTimeBlocked> periodOfTimeDayWeekBlocked;

    public DaysOfTheWeeksBlocked() {
        wholeDaysOfWeekBlocked = new HashSet<>();
        periodOfTimeDayWeekBlocked = new HashSet<>();
    }

    public boolean block(DayOfWeek dayOfWeek){
        return wholeDaysOfWeekBlocked.add(dayOfWeek);
    }

    public boolean block(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new InvalidBlockTimeException("Invalid range - Start time should not be after end time");
        }
        return periodOfTimeDayWeekBlocked.add(
                DayOfWeekTimeBlocked.builder()
                .dayOfWeek(dayOfWeek)
                .startTime(startTime)
                .endTime(endTime)
                .build()
        );
    }
}
