package br.com.agendaquiro.domain.calendar;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class CalendarSlotsBuilder {
    private CalendarSlots calendarSlots = new CalendarSlots();

    public CalendarSlotsBuilder block(DayOfWeek dayOfWeek) {
        calendarSlots.block(dayOfWeek);
        return this;
    }

    public CalendarSlots build() {
        return calendarSlots;
    }

    public CalendarSlotsBuilder block(DayOfWeek monday, LocalTime startTime, LocalTime endTime) {
        calendarSlots.block(monday, startTime, endTime);
        return this;
    }
}
