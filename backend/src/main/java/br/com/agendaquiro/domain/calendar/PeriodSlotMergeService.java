package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.freeappointmentsslots.PeriodSlot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodSlotMergeService {
    public List<PeriodSlot> mergeAppointmentsOnFreeSlots(List<PeriodSlot> freeSlots, List<PeriodSlot> appointments) {
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
        merged.addAll(appointments);
        return merged;
    }
}
