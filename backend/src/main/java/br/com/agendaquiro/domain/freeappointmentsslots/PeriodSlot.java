package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.appointment.Appointment;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@ToString
public class PeriodSlot {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public static List<PeriodSlot> from(List<Appointment> appointments) {
        List<PeriodSlot> slots = new ArrayList<>();
        for (Appointment appointment : appointments) {
            slots.add(PeriodSlot.builder()
                    .startTime(appointment.getStartTime().toLocalTime())
                    .endTime(appointment.getEndTime().toLocalTime())
                    .date(appointment.getStartTime().toLocalDate())
                    .build());
        }
        return slots;
    }
}
