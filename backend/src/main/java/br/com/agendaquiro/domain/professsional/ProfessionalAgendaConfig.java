package br.com.agendaquiro.domain.professsional;

import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlocked;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessionalAgendaConfig {

    private Professional professional;
    private DaysOfWeekBlocked daysOfWeekBlocked;
    private List<CustomRangeTimeBlocked> customRangesOfTimeBlocked;

}
