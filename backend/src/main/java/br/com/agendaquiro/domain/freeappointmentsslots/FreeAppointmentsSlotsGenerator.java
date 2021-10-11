package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.timeblockedconfig.PeriodTimeBlockedConfig;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class FreeAppointmentsSlotsGenerator {

    public FreeAppointmentsSlots generate(int durationInMinutes, LocalDateTime startDateTime, LocalDateTime endDateTime, ProfessionalBlockTimeConfig professionalBlockTimeConfig) {
        FreeAppointmentsSlots agendaOfPeriod = new FreeAppointmentsSlots();

        LocalDateTime currentTime = startDateTime;

        while (!currentTime.equals(endDateTime)) {
            LocalDate date = currentTime.toLocalDate();
            if (isNotWholeDayBlocked(date.getDayOfWeek(), professionalBlockTimeConfig)) {
                LocalTime startTime = currentTime.toLocalTime();
                currentTime = currentTime.plusMinutes(durationInMinutes);
                LocalTime endTime = currentTime.toLocalTime();
                if (isNotOnPeriodOfTimeBlocked(date.getDayOfWeek(), startTime, endTime, professionalBlockTimeConfig)) {
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

    private boolean isNotWholeDayBlocked(DayOfWeek dayOfWeek, ProfessionalBlockTimeConfig professionalBlockTimeConfig) {
        List<DayOfWeek> wholeDaysBlocked = professionalBlockTimeConfig.getWholeDaysOfWeekBlocked();
        return !wholeDaysBlocked.contains(dayOfWeek);
    }

    private boolean isNotOnPeriodOfTimeBlocked(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, ProfessionalBlockTimeConfig professionalBlockTimeConfig) {
        List<PeriodTimeBlockedConfig> periodOfPeriodTimeBlockedConfig = professionalBlockTimeConfig.getPeriodsOfTimesBlocked();
        for (PeriodTimeBlockedConfig dayOfWeekPeriodTimeBlockedConfig : periodOfPeriodTimeBlockedConfig) {
            if (dayOfWeekPeriodTimeBlockedConfig.isOnPeriodOfTime(dayOfWeek, startTime, endTime)) {
                return false;
            }
        }
        return true;
    }
}
