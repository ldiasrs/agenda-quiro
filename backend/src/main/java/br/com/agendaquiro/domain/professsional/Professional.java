package br.com.agendaquiro.domain.professsional;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "professional")
@Getter
@Setter
public class Professional {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

}
