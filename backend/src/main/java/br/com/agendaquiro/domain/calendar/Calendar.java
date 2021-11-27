package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.professsional.Professional;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class Calendar {
    private List<PeriodSlot> periodSlots;
    private Professional professional;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public Calendar(Professional professional, LocalDateTime startTime, LocalDateTime endTime, List<PeriodSlot> periodSlots) {
        this.professional = professional;
        this.startTime = startTime;
        this.endTime = endTime;
        this.periodSlots = periodSlots;
    }

    public void add(PeriodSlot slot) {
        periodSlots.add(slot);
    }

    public void addAll(List<PeriodSlot> slots) {
        periodSlots.addAll(slots);
    }
}
