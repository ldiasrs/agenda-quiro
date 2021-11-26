package br.com.agendaquiro.controller.calendar;

import br.com.agendaquiro.controller.BaseController;
import br.com.agendaquiro.domain.calendar.Calendar;
import br.com.agendaquiro.domain.calendar.CalendarService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.professionalservice.ProfessionalServiceCrudService;
import br.com.agendaquiro.domain.professsional.Professional;
import br.com.agendaquiro.domain.professsional.ProfessionalCrudService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.agendaquiro.config.PathMappings.CALENDAR;
import static br.com.agendaquiro.config.PathMappings.CALENDAR_FREE_SLOTS;
import static br.com.agendaquiro.controller.calendar.CalendarResponse.convertToRequestDto;

@RestController
public class CalendarController extends BaseController {

    private CalendarService calendarService;
    private ProfessionalCrudService professionalCrudService;

    public CalendarController(CalendarService calendarService, ProfessionalCrudService professionalCrudService) {
        this.calendarService = calendarService;
        this.professionalCrudService = professionalCrudService;
    }

    @GetMapping(CALENDAR_FREE_SLOTS)
    public String calendarFrom() {
        return "calendar working";
    }

    @GetMapping(CALENDAR)
    public ResponseEntity<CalendarResponse> getProfessionalCalendar(
            @PathVariable Long id,
            @RequestParam(value ="startPeriod", required = false) LocalDateTime startPeriod,
            @RequestParam(value ="endPeriod", required = false) LocalDateTime endPeriod) {
        Optional<Professional> optionProfessional = professionalCrudService.findById(id);
        if (!optionProfessional.isPresent()) {
            return super.responseMessage(
                    "Professional not found with ID: " + id,
                    HttpStatus.NOT_FOUND);
        }
        Calendar calendar = calendarService.getProfessionalCalendarByRange(
                optionProfessional.get(),
                startPeriod == null ?  LocalDateTime.now().minusDays(1).withSecond(0).withSecond(0) : startPeriod,
                endPeriod == null ?  LocalDateTime.now().plusDays(1).withSecond(0).withSecond(0) : endPeriod);
        return super.response(convertToRequestDto(calendar), HttpStatus.OK);
    }

}
