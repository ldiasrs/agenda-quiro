package br.com.agendaquiro.repository.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.DateTimeException;
import java.time.DayOfWeek;

/**
 * Converts {@link DayOfWeek} to {@link Integer} and back again.
 * Throws {@link DateTimeException} if the latter is not possible.
 */
@Converter(autoApply = true)
public class DayOfWeekToIntegerConverter implements AttributeConverter<DayOfWeek, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DayOfWeek attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public DayOfWeek convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : DayOfWeek.of(dbData);
    }
}
