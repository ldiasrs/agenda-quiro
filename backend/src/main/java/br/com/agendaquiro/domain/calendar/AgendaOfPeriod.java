package br.com.agendaquiro.domain.calendar;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AgendaOfPeriod {

    private List<PeriodSlot> periodSlots;

    public AgendaOfPeriod() {
        periodSlots = new ArrayList<>();
    }

    public boolean addPeriodSlot(PeriodSlot periodSlot) {
        return periodSlots.add(periodSlot);
    }
}
