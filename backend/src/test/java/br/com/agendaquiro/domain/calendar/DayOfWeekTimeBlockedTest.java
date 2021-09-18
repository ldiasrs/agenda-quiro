package br.com.agendaquiro.domain.calendar;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class DayOfWeekTimeBlockedTest {

    @Test
    public void shouldVeryPeriodOfDay() {
        DayOfWeekTimeBlocked blockedDayTime = DayOfWeekTimeBlocked.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .startTime(LocalTime.of(18, 0))
                .endTime(LocalTime.of(23, 59))
                .build();

        assertThat(
                blockedDayTime.isOnPeriodOfTime(
                        DayOfWeek.MONDAY,
                        LocalTime.of(19, 0),
                        LocalTime.of(20, 0)
                )
        ).isTrue();
    }

}
