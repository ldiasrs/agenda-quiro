package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.timeblockedconfig.InvalidBlockTimeException;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DaysOfTheWeeksBlockedTest {

    @Test(expected = InvalidBlockTimeException.class)
    public void shouldThrowInvalidRangeExeption() {
        new ProfessionalBlockTimeConfig()
                .block(DayOfWeek.MONDAY, LocalTime.of(19,0), LocalTime.of(17,0));
    }

    @Test
    public void shouldAcceptValidRange() {
        boolean added = new ProfessionalBlockTimeConfig()
                .block(DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(23, 0));
        assertThat(added).isTrue();
    }
}
