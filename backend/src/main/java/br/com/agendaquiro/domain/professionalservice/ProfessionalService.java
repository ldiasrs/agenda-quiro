package br.com.agendaquiro.domain.professionalservice;

import br.com.agendaquiro.domain.professsional.Professional;
import br.com.agendaquiro.domain.servicetype.ServiceType;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "PROFESSIONAL_SERVICE")
public class ProfessionalService {

    public ProfessionalService(Professional professional, ServiceType serviceType) {
        this.professional = professional;
        this.serviceType = serviceType;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PROFESSIONAL_ID")
    private Professional professional;

    @ManyToOne
    @JoinColumn(name = "SERVICE_TYPE_ID")
    private ServiceType serviceType;
}
