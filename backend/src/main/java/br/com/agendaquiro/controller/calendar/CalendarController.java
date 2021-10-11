package br.com.agendaquiro.controller.calendar;

import br.com.agendaquiro.controller.BaseController;
import br.com.agendaquiro.domain.calendar.Calendar;
import br.com.agendaquiro.domain.calendar.CalendarService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalServiceCrudService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.agendaquiro.config.PathMappings.CALENDAR;
import static br.com.agendaquiro.config.PathMappings.CALENDAR_FREE_SLOTS;
import static br.com.agendaquiro.controller.calendar.CalendarResponse.convertToRequestDto;

@RestController
public class CalendarController extends BaseController {

    private CalendarService calendarService;
    private ProfessionalServiceCrudService professionalServiceCrudService;

    public CalendarController(CalendarService calendarService, ProfessionalServiceCrudService professionalServiceCrudService) {
        this.calendarService = calendarService;
        this.professionalServiceCrudService = professionalServiceCrudService;
    }

    @GetMapping(CALENDAR_FREE_SLOTS)
    public String calendarFrom() {
        return "calendar working";
    }

    @GetMapping(CALENDAR)
    public ResponseEntity<CalendarResponse> getCalendar(GetCalendarRequest getCalendarRequest, Pageable pageable) {
        Optional<ProfessionalService> optionProfessional =
                professionalServiceCrudService.getProfessionalServiceById(
                        getCalendarRequest.getProfessionalServiceId());
        if (!optionProfessional.isPresent()) {
            return super.responseMessage(
                    "Professional not found with ID: " + getCalendarRequest.getProfessionalServiceId(),
                    HttpStatus.NOT_FOUND);
        }
        Calendar calendar = calendarService.getProfessionalCalendarByRange(
                optionProfessional.get(),
                getCalendarRequest.getStartTime(), getCalendarRequest.getEndTime());
        return super.response(convertToRequestDto(calendar), HttpStatus.OK);
    }

}
