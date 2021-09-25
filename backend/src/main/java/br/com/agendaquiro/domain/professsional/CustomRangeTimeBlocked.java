package br.com.agendaquiro.domain.professsional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "custom_range_time_blocked")
public class CustomRangeTimeBlocked {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "professional_agenda_config_id")
    private ProfessionalAgendaConfig professionalAgendaConfig;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
