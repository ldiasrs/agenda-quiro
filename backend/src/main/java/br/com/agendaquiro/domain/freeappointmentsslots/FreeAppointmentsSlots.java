package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.calendar.PeriodSlot;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FreeAppointmentsSlots {

    private List<PeriodSlot> periodSlots;

    public FreeAppointmentsSlots() {
        periodSlots = new ArrayList<>();
    }

    public boolean addPeriodSlot(PeriodSlot periodSlot) {
        return periodSlots.add(periodSlot);
    }
}
