package br.com.agendaquiro.controller.calendar;

import br.com.agendaquiro.controller.BaseController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static br.com.agendaquiro.config.PathMappings.CALENDAR;
import static br.com.agendaquiro.config.PathMappings.CALENDAR_FREE_SLOTS;

@RestController
public class CalendarController extends BaseController {

    @GetMapping(CALENDAR_FREE_SLOTS)
    public String calendarFrom() {
        return "calendar working";
    }

    @GetMapping(CALENDAR)
    public ResponseEntity<CalendarResponse> getCalendar(GetCalendarRequest getCalendarRequest, Pageable pageable) {
        List<SlotResponse> slotList = new ArrayList<>();
        CalendarResponse calendar = CalendarResponse.builder()
                .startTime(getCalendarRequest.getStartTime())
                .endTime(getCalendarRequest.getEndTime())
                .slots(slotList)
                .build();
        return super.response(calendar, HttpStatus.OK);
    }
}
