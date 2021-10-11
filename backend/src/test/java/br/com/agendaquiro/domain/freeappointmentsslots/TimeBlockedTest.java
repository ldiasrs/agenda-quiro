package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.timeblockedconfig.PeriodTimeBlockedConfig;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeBlockedTest {

    @Test
    public void shouldVeryPeriodOfDay() {
        PeriodTimeBlockedConfig blockedDayTime = PeriodTimeBlockedConfig.builder()
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
