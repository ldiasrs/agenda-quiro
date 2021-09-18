package br.com.agendaquiro.domain.agendanotblocked;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AgendaOfPeriodNotBlocked {

    private List<PeriodSlot> periodSlots;

    public AgendaOfPeriodNotBlocked() {
        periodSlots = new ArrayList<>();
    }

    public boolean addPeriodSlot(PeriodSlot periodSlot) {
        return periodSlots.add(periodSlot);
    }
}
