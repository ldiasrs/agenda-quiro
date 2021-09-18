package br.com.agendaquiro.domain.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class AgendaOfPeriodBuilder {

    private AgendaOfPeriod agendaOfPeriod = new AgendaOfPeriod();
    private int durationInMinutes;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private DaysOfTheWeeksBlocked daysOfTheWeeksBlocked;

    public AgendaOfPeriodBuilder period(int durationInMinutes, LocalDateTime monday, LocalDateTime saturday) {
        this.durationInMinutes = durationInMinutes;
        this.startDateTime = monday;
        this.endDateTime = saturday;
        return this;
    }

    public AgendaOfPeriodBuilder daysOfWeekBlocked(DaysOfTheWeeksBlocked daysOfTheWeeksBlocked) {
        this.daysOfTheWeeksBlocked = daysOfTheWeeksBlocked;
        return this;
    }

    public AgendaOfPeriod build() {
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

    private LocalDateTime setCurrentTimeToEndOfTimeBlocked(LocalDateTime currentTime) {
        Set<DayOfWeekTimeBlocked> periodOfTimeBlocked = daysOfTheWeeksBlocked.getPeriodOfTimeDayWeekBlocked();
        for (DayOfWeekTimeBlocked dayOfWeekTimeBlocked : periodOfTimeBlocked) {
            boolean isABlockedDay = dayOfWeekTimeBlocked.getDayOfWeek().equals(currentTime.getDayOfWeek());
            return currentTime
                    .withHour(dayOfWeekTimeBlocked.getEndTime().getHour())
                    .withMinute(dayOfWeekTimeBlocked.getEndTime().getMinute());
        }
        throw new RuntimeException("Could not find the day");
    }


    private boolean isNotWholeDayBlocked(DayOfWeek dayOfWeek) {
        Set<DayOfWeek> wholeDaysBlocked = daysOfTheWeeksBlocked.getWholeDaysOfWeekBlocked();
        return !wholeDaysBlocked.contains(dayOfWeek);
    }

    private boolean isNotOnPeriodOfTimeBlocked(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        Set<DayOfWeekTimeBlocked> periodOfTimeBlocked = daysOfTheWeeksBlocked.getPeriodOfTimeDayWeekBlocked();
        for (DayOfWeekTimeBlocked dayOfWeekTimeBlocked : periodOfTimeBlocked) {
            if (dayOfWeekTimeBlocked.isOnPeriodOfTime(dayOfWeek, startTime, endTime)) {
                return false;
            }
        }
        return true;
    }
}
