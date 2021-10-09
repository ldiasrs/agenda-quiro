package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.freeappointmentsslots.PeriodSlot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodSlotMergeService {
    public List<PeriodSlot> merge(List<PeriodSlot> periodsA, List<PeriodSlot> periodB) {
        return periodsA;
    }
}
