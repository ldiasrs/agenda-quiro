package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FreeAppointmentSlotsService {

    public FreeAppointmentsSlots generateFreeAppointmentSlots(int durationInMinutes, LocalDateTime startTime, LocalDateTime endTime, ProfessionalBlockTimeConfig professionalBlockTimeConfig) {
        return new FreeAppointmentsSlotsGenerator(
                durationInMinutes,
                startTime, endTime,
                professionalBlockTimeConfig)
                .generate();
    }

}
