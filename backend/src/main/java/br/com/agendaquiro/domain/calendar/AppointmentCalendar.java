package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.freeappointmentsslots.PeriodSlot;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AppointmentCalendar {
    private List<PeriodSlot> periodSlotList;
    private ProfessionalService professionalService;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public AppointmentCalendar(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        this.professionalService = professionalService;
        this.startDate = startDate;
        this.endDate = endDate;
        this.periodSlotList = new ArrayList<>();
    }

    public void add(PeriodSlot slot) {
        periodSlotList.add(slot);
    }

    public void addAll(List<PeriodSlot> slots) {
        periodSlotList.addAll(slots);
    }
}
