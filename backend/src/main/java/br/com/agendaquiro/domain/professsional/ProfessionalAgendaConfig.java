package br.com.agendaquiro.domain.professsional;

import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlocked;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "professional_agenda_config")
public class ProfessionalAgendaConfig {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(name = "PROFESSIONAL_ID")
    private Professional professional;

    @OneToMany(mappedBy = "professionalAgendaConfig")
    private List<DaysOfWeekBlocked> daysOfWeekBlocked;

    @OneToMany(mappedBy = "professionalAgendaConfig")
    private List<CustomRangeTimeBlocked> customRangesOfTimeBlocked;

}
