package br.com.agendaquiro.controller.calendar;

import br.com.agendaquiro.domain.calendar.Calendar;
import br.com.agendaquiro.domain.calendar.PeriodSlot;
import br.com.agendaquiro.domain.calendar.SlotStatus;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CalendarResponseTest {

    @Test
    public void convertToRequestDto() {
        Calendar calendar = Calendar.builder()
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusDays(1))
                .periodSlots(Arrays.asList(
                        PeriodSlot.builder()
                                .date(LocalDate.now())
                                .status(SlotStatus.FREE)
                                .startTime(LocalTime.now())
                                .endTime(LocalTime.now().plusHours(1))
                                .observation("descripton")
                                .build(),
                        PeriodSlot.builder()
                                .date(LocalDate.now())
                                .status(SlotStatus.SCHEDULED)
                                .startTime(LocalTime.now().plusHours(1))
                                .endTime(LocalTime.now().plusHours(2))
                                .observation("descripton")
                                .build()
                ))
                .build();
        CalendarResponse calendarResponse = CalendarResponse.convertToRequestDto(calendar);
        assertThat(calendarResponse.getStartTime()).isEqualTo(calendar.getStartTime());
        assertThat(calendarResponse.getEndTime()).isEqualTo(calendar.getEndTime());
        assertThat(calendarResponse.getPeriodSlots().size()).isEqualTo(calendar.getPeriodSlots().size());
    }
}
