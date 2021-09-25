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
public class ListTagStringToStringConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        StringBuilder sb = new StringBuilder();
        for (String string : attribute) {
            sb.append(";");
            sb.append(string);
        }
        return sb.toString().replaceFirst(";", ";").trim();
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        List<String> tags = new ArrayList<>();
        String[] values = dbData.split(";");
        for (String value : values) {
            tags.add(value);
        }
        return  tags;
    }
}
