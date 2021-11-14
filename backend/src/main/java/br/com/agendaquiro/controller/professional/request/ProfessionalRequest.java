package br.com.agendaquiro.controller.professional.request;

import br.com.agendaquiro.domain.professsional.Professional;
import lombok.*;
import org.modelmapper.ModelMapper;

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
