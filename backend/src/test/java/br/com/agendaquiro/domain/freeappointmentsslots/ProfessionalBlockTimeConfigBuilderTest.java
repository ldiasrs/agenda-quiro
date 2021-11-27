package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.professsional.Professional;
import br.com.agendaquiro.domain.timeblockedconfig.PeriodTimeBlockedConfig;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import br.com.agendaquiro.domain.timeblockedconfig.TimeBlockedConfigBuilder;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfessionalBlockTimeConfigBuilderTest {

    @Test
    public void shouldBlockDays() {
        ProfessionalBlockTimeConfig professionalBlockTimeConfig = new TimeBlockedConfigBuilder(new Professional())
                .block(DayOfWeek.SUNDAY)
                .block(DayOfWeek.SATURDAY)
                .build();
        assertThat(professionalBlockTimeConfig).isNotNull();
        assertThat(professionalBlockTimeConfig.getWholeDaysOfWeekBlocked()).contains(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY);
    }

    @Test
    public void shouldNotDuplicateBlockDays() {
        ProfessionalBlockTimeConfig professionalBlockTimeConfig = new TimeBlockedConfigBuilder(new Professional())
                .block(DayOfWeek.SUNDAY)
                .block(DayOfWeek.SUNDAY)
                .build();
        assertThat(professionalBlockTimeConfig).isNotNull();
        assertThat(professionalBlockTimeConfig.getWholeDaysOfWeekBlocked().size()).isEqualTo(1);
    }

    @Test
    public void shouldBlockTimeOfTheDay() {
        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime endTime = LocalTime.parse("10:00");
        DayOfWeek monday = DayOfWeek.MONDAY;
        ProfessionalBlockTimeConfig professionalBlockTimeConfig = new TimeBlockedConfigBuilder(new Professional())
                .block(monday, startTime, endTime)
                .build();
        PeriodTimeBlockedConfig dayOfWeekPeriodTimeBlockedConfig = professionalBlockTimeConfig.getPeriodsOfTimesBlocked().iterator().next();
        assertThat(dayOfWeekPeriodTimeBlockedConfig.getDayOfWeek()).isEqualTo(monday);
        assertThat(dayOfWeekPeriodTimeBlockedConfig.getStartTime()).isEqualTo(startTime);
        assertThat(dayOfWeekPeriodTimeBlockedConfig.getEndTime()).isEqualTo(endTime);
    }

    @Test
    public void shouldAllDaysPeriod() {
        //Gievn a start and end time
        //WHEN build for all days
        LocalTime firstStartBlockTime = LocalTime.of(18, 00);
        LocalTime firstEndBlockTime = LocalTime.of(23, 59);
        LocalTime secondStartBlockTime = LocalTime.of(00, 00);
        LocalTime secondEndBlockTime = LocalTime.of(10, 00);

        ProfessionalBlockTimeConfig professionalBlockTimeConfig = new TimeBlockedConfigBuilder(new Professional())
                .blockAllDays(firstStartBlockTime, firstEndBlockTime)
                .blockAllDays(secondStartBlockTime, secondEndBlockTime)
                .build();

        //THAN should block for all days
        List<PeriodTimeBlockedConfig> blockedTimes = professionalBlockTimeConfig.getPeriodsOfTimesBlocked();

        StringBuilder sb = new StringBuilder();
        for (PeriodTimeBlockedConfig blockedTime : blockedTimes) {
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
