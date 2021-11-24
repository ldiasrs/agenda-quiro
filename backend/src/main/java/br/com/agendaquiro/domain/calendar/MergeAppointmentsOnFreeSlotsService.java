package br.com.agendaquiro.domain.calendar;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MergeAppointmentsOnFreeSlotsService {

    public List<PeriodSlot> merge(List<PeriodSlot> freeSlots, List<PeriodSlot> appointments) {
        List<PeriodSlot> mergedSlots = new ArrayList<>();
        if (appointments != null && !appointments.isEmpty()) {
            List<PeriodSlot> nonConflictedFreeSlots = addNonConflictedFreeSlots(freeSlots, appointments);
            mergedSlots.addAll(appointments);
            mergedSlots.addAll(nonConflictedFreeSlots);
        } else {
            mergedSlots.addAll(freeSlots);
        }
        return mergedSlots;
    }

    private List<PeriodSlot> addNonConflictedFreeSlots(List<PeriodSlot> freeSlots, List<PeriodSlot> appointments) {
        List<PeriodSlot> merged = new ArrayList<>();
        for (PeriodSlot freeSlot : freeSlots) {
            for (PeriodSlot appointment : appointments) {
                if(freeSlot.getDate().equals(appointment.getDate())) {
                    if (rangesNotConflict(freeSlot, appointment)) {
                        merged.add(freeSlot);
                    }
                }
            }
        }
        return merged;
    }

    private boolean rangesNotConflict(PeriodSlot a, PeriodSlot b) {
        int aSt = a.getStartTime().toSecondOfDay();
        int aEd = a.getEndTime().toSecondOfDay();
        int bSt = b.getStartTime().toSecondOfDay();
        int bEd = b.getEndTime().toSecondOfDay();
        boolean aStartIsNotBRange = notIn(aSt, bSt, bEd);
        boolean aEndIsNotBRange = notIn(aEd, bSt, bEd);
        boolean bStartIsNotARange = notIn(bSt, aSt, aEd);
        boolean bEndIsNotARange = notIn(bEd, aSt, aEd);
        boolean aStartNotEqualBStart = aSt != bSt;
        return aStartIsNotBRange
                && aEndIsNotBRange
                && bStartIsNotARange
                && bEndIsNotARange
                && aStartNotEqualBStart;

    }

    private boolean notIn(int aSt, int bSt, int bEd) {
        return !((aSt > bSt) && (aSt < bEd));
    }

}


