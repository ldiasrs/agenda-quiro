package br.com.agendaquiro.domain.calendar;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AgendaBuilderTest {

    @Test
    public void shouldBuildAgenda() {
        //GIVEN A BLOCKED DAYS AND TIMES CONFIGURATION
        LocalTime blockAllDaysStartTime = LocalTime.of(18,0);
        LocalTime blockAllDaysEndTime = LocalTime.of(10,0);
        DaysOfTheWeeksBlocked daysOfTheWeeksBlocked = new DaysOfTheWeeksBlockedBuilder()
                .blockAllDays(blockAllDaysStartTime, LocalTime.of(23,59))
                .blockAllDays(LocalTime.of(00,00), blockAllDaysEndTime)
                .blockSunday()
                .build();

        //GIVEN A TIME PERIOD OF AGENDA (sunday-blocked, monday-free (10h-18))
        LocalDateTime sunday = LocalDateTime.of(2021, 9, 19, 00, 00);
        LocalDateTime tuesday = LocalDateTime.of(2021, 9, 21, 00, 00);
        //GIVEN A PERIOD OF TIME OF 60 min
        int durationInMinutes = 60;

        //WHEN ASKED TO CREATE AGENDA
        AgendaOfPeriod agendaOfPeriod = new AgendaOfPeriodBuilder()
                .period(durationInMinutes, sunday, tuesday)
                .daysOfWeekBlocked(daysOfTheWeeksBlocked)
                .build();

        //THEN the agenda should be fine
        List<PeriodSlot> periodSlots = agendaOfPeriod.getPeriodSlots();

        //THEN SHOULD HAVE CREATE 8 slots
        assertThat(periodSlots.size()).isEqualTo(8);
        LocalDateTime currentDateTimeVerification =  LocalDateTime.of(2021, 9, 20, 10, 00);;
        for (PeriodSlot periodSlot : periodSlots) {
            LocalDate date = periodSlot.getDate();
            LocalTime startTime = periodSlot.getStartTime();
            LocalTime endTime = periodSlot.getEndTime();

            assertThat(date).isEqualTo(currentDateTimeVerification.toLocalDate());
            assertThat(startTime).isEqualTo(currentDateTimeVerification.toLocalTime());

            currentDateTimeVerification = currentDateTimeVerification.plusMinutes(durationInMinutes);
            assertThat(endTime).isEqualTo(currentDateTimeVerification.toLocalTime());
        }
    }
}
