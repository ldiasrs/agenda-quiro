package br.com.agendaquiro.domain.professsional;

import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.daysofweekblocked.DaysOfWeekBlocked;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
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
    @JoinColumn(name = "day_of_week_blocked_id")
    private DaysOfWeekBlocked daysOfWeekBlocked;
}
