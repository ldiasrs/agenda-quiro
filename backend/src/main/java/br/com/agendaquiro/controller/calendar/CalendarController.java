package br.com.agendaquiro.controller.calendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.agendaquiro.config.PathMappings.CALENDAR_FREE_SLOTS;

@RestController
public class CalendarController {

    @GetMapping(CALENDAR_FREE_SLOTS)
    public String calendarFrom() {
        return "calendar working";
    }
}
