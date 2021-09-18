package br.com.agendaquiro.domain.agendanotblocked;

import br.com.agendaquiro.domain.daysofweekblocked.DayOfWeekTimeBlocked;
import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlocked;
import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlockedBuilder;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DaysOfWeekBlockedBuilderTest {

    @Test
    public void shouldBlockDays() {
        DaysOfWeekBlocked daysOfWeekBlocked = new DaysOfWeekBlockedBuilder()
                .block(DayOfWeek.SUNDAY)
                .block(DayOfWeek.SATURDAY)
                .build();
        assertThat(daysOfWeekBlocked).isNotNull();
        assertThat(daysOfWeekBlocked.getWholeDaysOfWeekBlocked()).contains(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY);
    }

    @Test
    public void shouldNotDuplicateBlockDays() {
        DaysOfWeekBlocked daysOfWeekBlocked = new DaysOfWeekBlockedBuilder()
                .block(DayOfWeek.SUNDAY)
                .block(DayOfWeek.SUNDAY)
                .build();
        assertThat(daysOfWeekBlocked).isNotNull();
        assertThat(daysOfWeekBlocked.getWholeDaysOfWeekBlocked().size()).isEqualTo(1);
    }

    @Test
    public void shouldBlockTimeOfTheDay() {
        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime endTime = LocalTime.parse("10:00");
        DayOfWeek monday = DayOfWeek.MONDAY;
        DaysOfWeekBlocked daysOfWeekBlocked = new DaysOfWeekBlockedBuilder()
                .block(monday, startTime, endTime)
                .build();
        DayOfWeekTimeBlocked dayOfWeekTimeBlocked = daysOfWeekBlocked.getPeriodOfTimeDayWeekBlocked().iterator().next();
        assertThat(dayOfWeekTimeBlocked.getDayOfWeek()).isEqualTo(monday);
        assertThat(dayOfWeekTimeBlocked.getStartTime()).isEqualTo(startTime);
        assertThat(dayOfWeekTimeBlocked.getEndTime()).isEqualTo(endTime);
    }

    @Test
    public void shouldAllDaysPeriod() {
        //Gievn a start and end time
        LocalTime startTime = LocalTime.parse("18:00");
        LocalTime endTime = LocalTime.parse("23:59");
        //WHEN build for all days
        DaysOfWeekBlocked daysOfWeekBlocked = new DaysOfWeekBlockedBuilder()
                .blockAllDays(startTime, endTime)
                .build();
        //THAN should block for all days
        Set<DayOfWeekTimeBlocked> blockedTimes = daysOfWeekBlocked.getPeriodOfTimeDayWeekBlocked();
        List<DayOfWeek> expectedDaysToBeBlocked = Arrays.asList(
                DayOfWeek.SUNDAY,
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY
        );
        List<DayOfWeek> verifiedDays = new ArrayList<>();
        for (DayOfWeek dayOfWeek : expectedDaysToBeBlocked) {
            for (DayOfWeekTimeBlocked blockedTime : blockedTimes) {
                if (blockedTime.getDayOfWeek().equals(dayOfWeek)) {
                    assertThat(blockedTime.getStartTime()).isEqualTo(startTime);
                    assertThat(blockedTime.getEndTime()).isEqualTo(endTime);
                    verifiedDays.add(dayOfWeek);
                    break;
                }
            }
        }
        assertThat(verifiedDays).containsAll(expectedDaysToBeBlocked);
    }
}
