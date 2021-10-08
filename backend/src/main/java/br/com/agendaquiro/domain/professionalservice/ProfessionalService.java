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
@Table(name = "professional_service_type")
public class ProfessionalService {

    public ProfessionalService(Professional professional, ServiceType serviceType) {
        this.professional = professional;
        this.serviceType = serviceType;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(name = "PROFESSIONAL_ID")
    private Professional professional;

    @OneToOne
    @JoinColumn(name = "SERVICE_TYPE_ID")
    private ServiceType serviceType;
}
