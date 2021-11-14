package br.com.agendaquiro.controller.professional.request;

import br.com.agendaquiro.controller.customer.request.CustomerRequest;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.professsional.Professional;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionalRequest  implements Serializable {

    private Long id;
    private String name;

    public static Professional convertToEntity(ProfessionalRequest professionalRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(professionalRequest, Professional.class);
    }

    public static ProfessionalRequest convertToRequestDto(Professional professional) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(professional, ProfessionalRequest.class);
    }
}
