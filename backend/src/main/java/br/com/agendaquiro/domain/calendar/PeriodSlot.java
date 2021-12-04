package br.com.agendaquiro.domain.calendar;

import br.com.agendaquiro.domain.appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class PeriodSlot {
    private Long appointmentId;
    private SlotStatus status;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private String observation;
    private String clientName;
    private String clientPhone;
    private String serviceName;
    private BigDecimal amountPaid;

    public PeriodSlot() {
        this.date =  LocalDate.now();
        this.startTime = LocalTime.now();
        this.endTime = LocalTime.now();
        this.observation = "--";
        this.status = SlotStatus.FREE;
    }

    public static List<PeriodSlot> from(List<Appointment> appointments) {
        List<PeriodSlot> slots = new ArrayList<>();
        if (appointments != null && !appointments.isEmpty()) {
            for (Appointment appointment : appointments) {
                String serviceDescription = appointment.getProfessionalService().getServiceType().getDescription();
                String clientName = appointment.getCustomer().getName();
                String clientPhone = appointment.getCustomer().getPhone();
                slots.add(PeriodSlot.builder()
                        .startTime(appointment.getStartTime().toLocalTime())
                        .endTime(appointment.getEndTime().toLocalTime())
                        .date(appointment.getStartTime().toLocalDate())
                        .status(SlotStatus.SCHEDULED)
                        .clientName(clientName)
                        .clientPhone(clientPhone)
                        .serviceName(serviceDescription)
                        .observation(appointment.getObservation())
                        .amountPaid(appointment.getAmountPaid())
                        .appointmentId(appointment.getId())
                        .build());
            }
        }
        return slots;
    }
}
