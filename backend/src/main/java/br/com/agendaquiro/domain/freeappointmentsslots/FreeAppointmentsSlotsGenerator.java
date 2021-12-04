package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.calendar.PeriodSlot;
import br.com.agendaquiro.domain.calendar.SlotStatus;
import br.com.agendaquiro.domain.timeblockedconfig.PeriodTimeBlockedConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class FreeAppointmentsSlotsGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreeAppointmentsSlotsGenerator.class);

    public FreeAppointmentsSlots generate(int durationInMinutes,
                                          LocalDateTime startDateTime, LocalDateTime endDateTime,
                                          List<PeriodTimeBlockedConfig> periodsOfTimesBlocked,
                                          List<DayOfWeek> wholeDaysOfWeekBlocked) {
        FreeAppointmentsSlots agendaOfPeriod = new FreeAppointmentsSlots();

        LocalDateTime currentTime = startDateTime;

        while (currentTime.isBefore(endDateTime)) {
            LocalDate date = currentTime.toLocalDate();
            LOGGER.trace("Current time: " + currentTime);
            if (isNotWholeDayBlocked(date.getDayOfWeek(), wholeDaysOfWeekBlocked)) {
                LocalTime startTime = currentTime.toLocalTime();
                currentTime = currentTime.plusMinutes(durationInMinutes);
                LocalTime endTime = currentTime.toLocalTime();
                LOGGER.trace("Not a blocking day, defining period: " + startTime + "/" + endTime);
                if (isNotOnPeriodOfTimeBlocked(date.getDayOfWeek(), startTime, endTime, periodsOfTimesBlocked)) {
                    LOGGER.trace("Adding as a FREE period: " + startTime + "/" + endTime);
                    agendaOfPeriod.addPeriodSlot(
                            PeriodSlot.builder()
                                    .date(date)
                                    .startTime(startTime)
                                    .observation("--")
                                    .status(SlotStatus.FREE)
                                    .endTime(endTime)
                                    .build()
                    );
                } else {
                    LOGGER.trace("CONFLICT period: " + startTime + "/" + endTime
                            + "Professional blocked times: " + periodsOfTimesBlocked);
                }
            } else {
                currentTime = currentTime.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
                LOGGER.trace("Whole day blocked defining current time" + currentTime);
            }
        }
        LOGGER.trace("End of free slots defining number of free slots: " + agendaOfPeriod.getPeriodSlots().size());
        return agendaOfPeriod;
    }

    private boolean isNotWholeDayBlocked(DayOfWeek dayOfWeek, List<DayOfWeek> wholeDaysOfWeekBlocked) {
        boolean flag = !wholeDaysOfWeekBlocked.contains(dayOfWeek);
        LOGGER.trace("isNotWholeDayBlocked: " + flag +
                " DayOfWeek: " + dayOfWeek + " wholeDaysOfWeekBlocked: " + wholeDaysOfWeekBlocked);
        return flag;
    }

    private boolean isNotOnPeriodOfTimeBlocked(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, List<PeriodTimeBlockedConfig> periodOfPeriodTimeBlockedConfig) {
        for (PeriodTimeBlockedConfig dayOfWeekPeriodTimeBlockedConfig : periodOfPeriodTimeBlockedConfig) {
            if (dayOfWeekPeriodTimeBlockedConfig.isOnPeriodOfTime(dayOfWeek, startTime, endTime)) {
                return false;
            }
        }
        return true;
    }
}
