package br.com.agendaquiro.domain.timeblockedconfig;

import br.com.agendaquiro.domain.professionalservice.ProfessionalService;
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
@Table(name = "PROFESSIONAL_BLOCK_TIME_CONFIG")
public class ProfessionalBlockTimeConfig {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Convert(converter = ListDayOfWeekToIntegerConverter.class)
    private List<DayOfWeek> wholeDaysOfWeekBlocked;

    @OneToMany(
            mappedBy = "professionalBlockTimeConfig",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PeriodTimeBlockedConfig> periodsOfTimesBlocked;

    @ManyToOne
    @JoinColumn(name = "PROFESSIONAL_SERVICE_ID")
    private ProfessionalService professionalService;

    public ProfessionalBlockTimeConfig(ProfessionalService professionalService) {
        this();
        this.professionalService = professionalService;
    }

    public ProfessionalBlockTimeConfig() {
        wholeDaysOfWeekBlocked = new ArrayList<>();
        periodsOfTimesBlocked = new ArrayList<>();
    }

    public boolean block(DayOfWeek dayOfWeek){
        if (!wholeDaysOfWeekBlocked.contains(dayOfWeek)){
            return wholeDaysOfWeekBlocked.add(dayOfWeek);
        }
       return false;
    }

    public boolean block(PeriodTimeBlockedConfig dayOfWeekPeriodTimeBlockedConfig) {
        return periodsOfTimesBlocked.add(dayOfWeekPeriodTimeBlockedConfig);
    }

    public boolean block(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new InvalidBlockTimeException("Invalid range - Start time should not be after end time");
        }
        return periodsOfTimesBlocked.add(
                PeriodTimeBlockedConfig.builder()
                .dayOfWeek(dayOfWeek)
                .startTime(startTime)
                .endTime(endTime)
                .build()
        );
    }
}
