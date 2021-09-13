package br.com.agendaquiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @GetMapping("schedulings")
    public String calendarFrom() {
        return "calendar working";
    }
}
