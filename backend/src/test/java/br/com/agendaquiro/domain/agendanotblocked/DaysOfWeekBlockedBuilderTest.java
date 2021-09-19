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
        //WHEN build for all days
        LocalTime firstStartBlockTime = LocalTime.of(18, 00);
        LocalTime firstEndBlockTime = LocalTime.of(23, 59);
        LocalTime secondStartBlockTime = LocalTime.of(00, 00);
        LocalTime secondEndBlockTime = LocalTime.of(10, 00);

        DaysOfWeekBlocked daysOfWeekBlocked = new DaysOfWeekBlockedBuilder()
                .blockAllDays(firstStartBlockTime, firstEndBlockTime)
                .blockAllDays(secondStartBlockTime, secondEndBlockTime)
                .build();

        //THAN should block for all days
        List<DayOfWeekTimeBlocked> blockedTimes = daysOfWeekBlocked.getPeriodOfTimeDayWeekBlocked();

        StringBuilder sb = new StringBuilder();
        for (DayOfWeekTimeBlocked blockedTime : blockedTimes) {
            sb.append(" #DayOfWeek: " + blockedTime.getDayOfWeek() + " start: " + blockedTime.getStartTime() + " end: " + blockedTime.getEndTime());
        }

        assertThat(sb.toString()).isEqualTo(
                " #DayOfWeek: MONDAY start: 18:00 end: 23:59" +
                " #DayOfWeek: MONDAY start: 00:00 end: 10:00" +
                " #DayOfWeek: TUESDAY start: 18:00 end: 23:59" +
                " #DayOfWeek: TUESDAY start: 00:00 end: 10:00" +
                " #DayOfWeek: WEDNESDAY start: 18:00 end: 23:59" +
                " #DayOfWeek: WEDNESDAY start: 00:00 end: 10:00" +
                " #DayOfWeek: THURSDAY start: 18:00 end: 23:59" +
                " #DayOfWeek: THURSDAY start: 00:00 end: 10:00" +
                " #DayOfWeek: FRIDAY start: 18:00 end: 23:59" +
                " #DayOfWeek: FRIDAY start: 00:00 end: 10:00" +
                " #DayOfWeek: SATURDAY start: 18:00 end: 23:59" +
                " #DayOfWeek: SATURDAY start: 00:00 end: 10:00" +
                " #DayOfWeek: SUNDAY start: 18:00 end: 23:59" +
                " #DayOfWeek: SUNDAY start: 00:00 end: 10:00");
    }
}
