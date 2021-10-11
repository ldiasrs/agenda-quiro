package br.com.agendaquiro.controller.calendar;

import br.com.agendaquiro.controller.customer.request.CustomerRequest;
import br.com.agendaquiro.domain.calendar.Calendar;
import br.com.agendaquiro.domain.customer.Customer;
import lombok.Builder;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
public class CalendarResponse {
    private LocalDate startTime;
    private LocalDate endTime;
    private List<SlotResponse> slots;

    public static CalendarResponse convertToRequestDto(Calendar calendar) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(calendar, CalendarResponse.class);
    }
}
