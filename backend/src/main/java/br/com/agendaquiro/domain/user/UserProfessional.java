package br.com.agendaquiro.domain.user;

import br.com.agendaquiro.domain.professsional.Professional;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "USER_PROFESSIONAL")
public class UserProfessional {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    private User user;
    @OneToOne
    private Professional professional;
}
