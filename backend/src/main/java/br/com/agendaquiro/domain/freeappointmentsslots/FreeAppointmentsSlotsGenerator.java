package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.timeblockedconfig.TimeBlockedConfig;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class FreeAppointmentsSlotsGenerator {

    private FreeAppointmentsSlots agendaOfPeriod = new FreeAppointmentsSlots();
    private int durationInMinutes;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private ProfessionalBlockTimeConfig professionalBlockTimeConfig;

    public  FreeAppointmentsSlotsGenerator(int durationInMinutes, LocalDateTime startTime, LocalDateTime endTime, ProfessionalBlockTimeConfig professionalBlockTimeConfig) {
        this.durationInMinutes = durationInMinutes;
        this.startDateTime = startTime;
        this.endDateTime = endTime;
        this.professionalBlockTimeConfig = professionalBlockTimeConfig;
    }

    public FreeAppointmentsSlots generate() {
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
        List<DayOfWeek> wholeDaysBlocked = professionalBlockTimeConfig.getWholeDaysOfWeekBlocked();
        return !wholeDaysBlocked.contains(dayOfWeek);
    }

    private boolean isNotOnPeriodOfTimeBlocked(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        List<TimeBlockedConfig> periodOfTimeBlockedConfig = professionalBlockTimeConfig.getPeriodOfTimeDayWeekBlocked();
        for (TimeBlockedConfig dayOfWeekTimeBlockedConfig : periodOfTimeBlockedConfig) {
            if (dayOfWeekTimeBlockedConfig.isOnPeriodOfTime(dayOfWeek, startTime, endTime)) {
                return false;
            }
        }
        return true;
    }
}
