package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.freeappointmentsslots.PeriodSlot;
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
                    boolean freeSlotStartIsAfterAppointmentStart = freeSlot.getStartTime().isAfter(appointment.getStartTime());
                    boolean freeSlotStartIsEqualAppointmentStart = freeSlot.getStartTime().equals(appointment.getStartTime());
                    boolean freeStartTimeIsAfterOrEqualsAppointmentStartTime = (freeSlotStartIsAfterAppointmentStart || freeSlotStartIsEqualAppointmentStart);
                    boolean freeSlotStartIsBeforeAppointmentStartAppointmentEnd = freeSlot.getStartTime().isBefore(appointment.getEndTime());
                    boolean isFreeSlotStartBetweenAppointmentTime = (freeStartTimeIsAfterOrEqualsAppointmentStartTime && freeSlotStartIsBeforeAppointmentStartAppointmentEnd);
                    if (!isFreeSlotStartBetweenAppointmentTime) {
                        merged.add(freeSlot);
                    }
                }
            }
        }
        return merged;
    }
}
