package br.com.agendaquiro.domain.daysofweekblocked;

import br.com.agendaquiro.domain.User;
import br.com.agendaquiro.domain.professsional.ProfessionalAgendaConfig;
import br.com.agendaquiro.repository.converter.DayOfWeekToIntegerConverter;
import br.com.agendaquiro.repository.converter.ListDayOfWeekToIntegerConverter;
import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "days_of_week_blocked")
public class DaysOfWeekBlocked {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Convert(converter = ListDayOfWeekToIntegerConverter.class)
    private List<DayOfWeek> wholeDaysOfWeekBlocked;

    @OneToMany
    @JoinColumn(name = "day_of_week_time_blocked_id")
    private List<DayOfWeekTimeBlocked> periodOfTimeDayWeekBlocked;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "professional_agenda_config_id")
    private ProfessionalAgendaConfig professionalAgendaConfig;

    public DaysOfWeekBlocked() {
        wholeDaysOfWeekBlocked = new ArrayList<>();
        periodOfTimeDayWeekBlocked = new ArrayList<>();
    }

    public boolean block(DayOfWeek dayOfWeek){
        if (!wholeDaysOfWeekBlocked.contains(dayOfWeek)){
            return wholeDaysOfWeekBlocked.add(dayOfWeek);
        }
       return false;
    }

    public boolean block(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new InvalidBlockTimeException("Invalid range - Start time should not be after end time");
        }
        return periodOfTimeDayWeekBlocked.add(
                DayOfWeekTimeBlocked.builder()
                .dayOfWeek(dayOfWeek)
                .startTime(startTime)
                .endTime(endTime)
                .build()
        );
    }
}
