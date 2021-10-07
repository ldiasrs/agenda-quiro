package br.com.agendaquiro.domain.professsional;

import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlocked;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "professional_config")
public class ProfessionalConfig {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @OneToOne
    @JoinColumn(name = "days_of_week_blocked_id")
    private DaysOfWeekBlocked daysOfWeekBlocked;
}
