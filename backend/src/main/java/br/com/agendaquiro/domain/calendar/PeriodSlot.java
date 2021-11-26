package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class PeriodSlot {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;
    private SlotStatus status;

    public PeriodSlot() {
        this.date =  LocalDate.now();
        this.startTime = LocalTime.now();
        this.endTime = LocalTime.now();
        this.description = "--";
        this.status = SlotStatus.FREE;
    }

    public static List<PeriodSlot> from(List<Appointment> appointments) {
        List<PeriodSlot> slots = new ArrayList<>();
        if (appointments != null && !appointments.isEmpty()) {
            for (Appointment appointment : appointments) {
                slots.add(PeriodSlot.builder()
                        .startTime(appointment.getStartTime().toLocalTime())
                        .endTime(appointment.getEndTime().toLocalTime())
                        .date(appointment.getStartTime().toLocalDate())
                        .status(SlotStatus.SCHEDULED)
                        .description(appointment.getCustomer().getName())
                        .build());
            }
        }
        return slots;
    }
}
