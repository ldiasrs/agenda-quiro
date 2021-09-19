package br.com.agendaquiro.domain.daysofweekblocked;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class DaysOfWeekBlocked {

    private List<DayOfWeek> wholeDaysOfWeekBlocked;
    private List<DayOfWeekTimeBlocked> periodOfTimeDayWeekBlocked;

    public DaysOfWeekBlocked() {
        wholeDaysOfWeekBlocked = new ArrayList<>();
        periodOfTimeDayWeekBlocked = new ArrayList<>();
    }

    public boolean block(DayOfWeek dayOfWeek){
        if (!wholeDaysOfWeekBlocked.contains(dayOfWeek)){
            return wholeDaysOfWeekBlocked.add(dayOfWeek);
        }
       return false;
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
