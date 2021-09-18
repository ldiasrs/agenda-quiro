package br.com.agendaquiro.domain.calendar;

import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Builder
@Getter
public class DayOfWeekTimeBlocked {

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public boolean isOnPeriodOfTime(DayOfWeek otherDay, LocalTime otherStart, LocalTime otherEnd) {
        boolean isABlockedDay = dayOfWeek.equals(otherDay);
        boolean isAfterABlockedHour = otherStart.isAfter(startTime) || otherStart.equals(startTime);
        boolean isBeforeABlockedHour = otherEnd.isBefore(endTime) || otherEnd.equals(endTime);
        return isABlockedDay && isAfterABlockedHour && isBeforeABlockedHour;
    }
}
