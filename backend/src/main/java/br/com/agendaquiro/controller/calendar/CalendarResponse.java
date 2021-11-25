package br.com.agendaquiro.controller.calendar;

import br.com.agendaquiro.domain.calendar.Calendar;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarResponse {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<PeriodSlotResponse> periodSlots;

    public static CalendarResponse convertToRequestDto(Calendar calendar) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(calendar, CalendarResponse.class);
    }
}
