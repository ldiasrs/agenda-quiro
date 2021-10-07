package br.com.agendaquiro.controller.calendar;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public class CalendarResponse {
    private LocalDate startTime;
    private LocalDate endTime;
    private List<SlotResponse> slots;
}
