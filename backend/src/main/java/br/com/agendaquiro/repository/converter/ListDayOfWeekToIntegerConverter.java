package br.com.agendaquiro.repository.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts {@link DayOfWeek} to {@link Integer} and back again.
 * Throws {@link DateTimeException} if the latter is not possible.
 */
@Converter(autoApply = true)
public class ListDayOfWeekToIntegerConverter implements AttributeConverter<List<DayOfWeek>, String> {

    @Override
    public String convertToDatabaseColumn(List<DayOfWeek> attribute) {
        StringBuilder sb = new StringBuilder();
        for (DayOfWeek dayOfWeek : attribute) {
            sb.append(":");
            sb.append(dayOfWeek.getValue());
        }
        return sb.toString().replaceFirst(":", "").trim();
    }

    @Override
    public List<DayOfWeek> convertToEntityAttribute(String dbData) {
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        String[] values = dbData.split(":");
        for (String value : values) {
            dayOfWeeks.add(DayOfWeek.of(Integer.valueOf(value)));
        }
        return  dayOfWeeks;
    }
}
