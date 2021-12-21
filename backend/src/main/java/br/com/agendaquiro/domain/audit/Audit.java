package br.com.agendaquiro.domain.audit;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "AUDIT")
@Getter
@Setter
public class Audit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDateTime dateTime;
    private String auditDescription;

}
