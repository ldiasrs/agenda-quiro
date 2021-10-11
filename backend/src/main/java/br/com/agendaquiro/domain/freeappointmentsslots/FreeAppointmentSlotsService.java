package br.com.agendaquiro.domain.freeappointmentsslots;

import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfig;
import br.com.agendaquiro.domain.timeblockedconfig.ProfessionalBlockTimeConfigRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FreeAppointmentSlotsService {

    private ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository;
    private FreeAppointmentsSlotsGenerator freeAppointmentsSlotsGenerator;

    public FreeAppointmentSlotsService(ProfessionalBlockTimeConfigRepository professionalBlockTimeConfigRepository, FreeAppointmentsSlotsGenerator freeAppointmentsSlotsGenerator) {
        this.professionalBlockTimeConfigRepository = professionalBlockTimeConfigRepository;
        this.freeAppointmentsSlotsGenerator = freeAppointmentsSlotsGenerator;
    }

    public FreeAppointmentsSlots getFreeAppointmentsSlots(ProfessionalService professionalService, LocalDateTime startDate, LocalDateTime endDate) {
        ProfessionalBlockTimeConfig timeBlockedConfig =
                professionalBlockTimeConfigRepository.findByProfessionalServiceId(professionalService.getId());
        FreeAppointmentsSlots freeAppointments =
                freeAppointmentsSlotsGenerator.generate(
                        professionalService.getServiceType().getDurationInMinutes(),
                                startDate, endDate, timeBlockedConfig);
        return freeAppointments;
    }

}
