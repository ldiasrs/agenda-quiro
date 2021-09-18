package br.com.agendaquiro.domain.agendanotblocked;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DaysOfTheWeeksBlockedBuilderTest {

    @Test
    public void shouldBlockDays() {
        DaysOfTheWeeksBlocked daysOfTheWeeksBlocked = new DaysOfTheWeeksBlockedBuilder()
                .block(DayOfWeek.SUNDAY)
                .block(DayOfWeek.SATURDAY)
                .build();
        assertThat(daysOfTheWeeksBlocked).isNotNull();
        assertThat(daysOfTheWeeksBlocked.getWholeDaysOfWeekBlocked()).contains(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY);
    }

    @Test
    public void shouldNotDuplicateBlockDays() {
        DaysOfTheWeeksBlocked daysOfTheWeeksBlocked = new DaysOfTheWeeksBlockedBuilder()
                .block(DayOfWeek.SUNDAY)
                .block(DayOfWeek.SUNDAY)
                .build();
        assertThat(daysOfTheWeeksBlocked).isNotNull();
        assertThat(daysOfTheWeeksBlocked.getWholeDaysOfWeekBlocked().size()).isEqualTo(1);
    }

    @Test
    public void shouldBlockTimeOfTheDay() {
        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime endTime = LocalTime.parse("10:00");
        DayOfWeek monday = DayOfWeek.MONDAY;
        DaysOfTheWeeksBlocked daysOfTheWeeksBlocked = new DaysOfTheWeeksBlockedBuilder()
                .block(monday, startTime, endTime)
                .build();
        DayOfWeekTimeBlocked dayOfWeekTimeBlocked = daysOfTheWeeksBlocked.getPeriodOfTimeDayWeekBlocked().iterator().next();
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
        DaysOfTheWeeksBlocked daysOfTheWeeksBlocked = new DaysOfTheWeeksBlockedBuilder()
                .blockAllDays(startTime, endTime)
                .build();
        //THAN should block for all days
        Set<DayOfWeekTimeBlocked> blockedTimes = daysOfTheWeeksBlocked.getPeriodOfTimeDayWeekBlocked();
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
