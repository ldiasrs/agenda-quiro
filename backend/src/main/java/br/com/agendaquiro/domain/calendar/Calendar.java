package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.freeappointmentsslots.PeriodSlot;
import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Calendar {
    private List<PeriodSlot> periodSlots;
    private ProfessionalService professionalService;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public Calendar(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        this.professionalService = professionalService;
        this.startDate = startDate;
        this.endDate = endDate;
        this.periodSlots = new ArrayList<>();
    }

    public void add(PeriodSlot slot) {
        periodSlots.add(slot);
    }

    public void addAll(List<PeriodSlot> slots) {
        periodSlots.addAll(slots);
    }
}
