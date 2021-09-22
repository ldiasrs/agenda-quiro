package br.com.agendaquiro.repository.converter;

import org.junit.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListDayOfWeekToIntegerConverterTest {

    @Test
    public void shouldConvertListToString() {
        List<DayOfWeek> days = new ArrayList<>();
        days.add(DayOfWeek.SATURDAY);
        days.add(DayOfWeek.SUNDAY);
        days.add(DayOfWeek.MONDAY);
        String result = new ListDayOfWeekToIntegerConverter().convertToDatabaseColumn(days);
        assertThat(result).isEqualTo("6:7:1");
    }

    @Test
    public void shouldConvertStringToList() {
        List<DayOfWeek> days = new ArrayList<>();
        days.add(DayOfWeek.SATURDAY);
        days.add(DayOfWeek.SUNDAY);
        days.add(DayOfWeek.MONDAY);
        List<DayOfWeek> result = new ListDayOfWeekToIntegerConverter().convertToEntityAttribute("6:7:1");
        assertThat(result).containsAll(days);
    }

}
