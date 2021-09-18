package br.com.agendaquiro.domain.calendar;

import junit.framework.TestCase;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class CalendarSlotsBuilderTest extends TestCase {

    @Test
    public void shouldBlockDays() {
        CalendarSlots calendarSlots = new CalendarSlotsBuilder()
                .block(DayOfWeek.SUNDAY)
                .block(DayOfWeek.SATURDAY)
                .build();
        assertThat(calendarSlots).isNotNull();
        assertThat(calendarSlots.getWholeDaysOfWeekBlocked()).contains(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY);
    }

    @Test
    public void shouldNotDuplicateBlockDays() {
        CalendarSlots calendarSlots = new CalendarSlotsBuilder()
                .block(DayOfWeek.SUNDAY)
                .block(DayOfWeek.SUNDAY)
                .build();
        assertThat(calendarSlots).isNotNull();
        assertThat(calendarSlots.getWholeDaysOfWeekBlocked().size()).isEqualTo(1);
    }

    @Test
    public void shouldBlockTimeOfTheDay() {
        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime endTime = LocalTime.parse("10:00");
        DayOfWeek monday = DayOfWeek.MONDAY;
        CalendarSlots calendarSlots = new CalendarSlotsBuilder()
                .block(monday, startTime, endTime)
                .build();
        DayOfWeekTimeBlocked dayOfWeekTimeBlocked = calendarSlots.getPeriodOfTimeDayWeekBlocked().iterator().next();
        assertThat(dayOfWeekTimeBlocked.getDayOfWeek()).isEqualTo(monday);
        assertThat(dayOfWeekTimeBlocked.getStartTime()).isEqualTo(startTime);
        assertThat(dayOfWeekTimeBlocked.getEndTime()).isEqualTo(endTime);
    }
}
