package br.com.agendaquiro.domain.timeblockedconfig;

import br.com.agendaquiro.repository.converter.DayOfWeekToIntegerConverter;
import br.com.agendaquiro.repository.converter.LocalTimeToTimeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Builder
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TIME_BLOCKED_CONFIG")
public class TimeBlockedConfig {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Convert(converter = DayOfWeekToIntegerConverter.class)
    private DayOfWeek dayOfWeek;
    @Convert(converter = LocalTimeToTimeConverter.class)
    private LocalTime startTime;
    @Convert(converter = LocalTimeToTimeConverter.class)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "PROFESSIONAL_BLOCK_TIME_CONFIG_ID")
    private ProfessionalBlockTimeConfig professionalBlockTimeConfig;

    public boolean isOnPeriodOfTime(DayOfWeek otherDay, LocalTime otherStart, LocalTime otherEnd) {
        boolean isABlockedDay = dayOfWeek.equals(otherDay);
        boolean isAfterABlockedHour = otherStart.isAfter(startTime) || otherStart.equals(startTime);
        boolean isBeforeABlockedHour = otherEnd.isBefore(endTime) || otherEnd.equals(endTime);
        return isABlockedDay && isAfterABlockedHour && isBeforeABlockedHour;
    }

}
