package br.com.agendaquiro.domain.agendanotblocked;

import br.com.agendaquiro.domain.daysofweekblocked.DayOfWeekTimeBlocked;
import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlocked;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class AgendaOfPeriodNotBlockedBuilder {

    private AgendaOfPeriodNotBlocked agendaOfPeriod = new AgendaOfPeriodNotBlocked();
    private int durationInMinutes;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private DaysOfWeekBlocked daysOfWeekBlocked;

    public AgendaOfPeriodNotBlockedBuilder period(int durationInMinutes, LocalDateTime monday, LocalDateTime saturday) {
        this.durationInMinutes = durationInMinutes;
        this.startDateTime = monday;
        this.endDateTime = saturday;
        return this;
    }

    public AgendaOfPeriodNotBlockedBuilder daysOfWeekBlocked(DaysOfWeekBlocked daysOfWeekBlocked) {
        this.daysOfWeekBlocked = daysOfWeekBlocked;
        return this;
    }

    public AgendaOfPeriodNotBlocked build() {
        LocalDateTime currentTime = startDateTime;

        while (!currentTime.equals(endDateTime)) {
            LocalDate date = currentTime.toLocalDate();
            if (isNotWholeDayBlocked(date.getDayOfWeek())) {
                LocalTime startTime = currentTime.toLocalTime();
                currentTime = currentTime.plusMinutes(durationInMinutes);
                LocalTime endTime = currentTime.toLocalTime();
                if (isNotOnPeriodOfTimeBlocked(date.getDayOfWeek(), startTime, endTime)) {
                    agendaOfPeriod.addPeriodSlot(
                            PeriodSlot.builder()
                                    .date(date)
                                    .startTime(startTime)
                                    .endTime(endTime)
                                    .build()
                    );
                }
            } else {
                currentTime = currentTime.plusDays(1).withHour(0).withMinute(0);
            }

        }
        return agendaOfPeriod;
    }

    private boolean isNotWholeDayBlocked(DayOfWeek dayOfWeek) {
        List<DayOfWeek> wholeDaysBlocked = daysOfWeekBlocked.getWholeDaysOfWeekBlocked();
        return !wholeDaysBlocked.contains(dayOfWeek);
    }

    private boolean isNotOnPeriodOfTimeBlocked(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        List<DayOfWeekTimeBlocked> periodOfTimeBlocked = daysOfWeekBlocked.getPeriodOfTimeDayWeekBlocked();
        for (DayOfWeekTimeBlocked dayOfWeekTimeBlocked : periodOfTimeBlocked) {
            if (dayOfWeekTimeBlocked.isOnPeriodOfTime(dayOfWeek, startTime, endTime)) {
                return false;
            }
        }
        return true;
    }
}
