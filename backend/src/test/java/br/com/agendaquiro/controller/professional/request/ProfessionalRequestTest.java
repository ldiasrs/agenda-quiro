package br.com.agendaquiro.controller.professional.request;

import br.com.agendaquiro.domain.professsional.Professional;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ProfessionalRequestTest {

    @Test
    public void convertToEntity() {
        ProfessionalRequest request = ProfessionalRequest.builder().name("Test").build();
        Professional entity = ProfessionalRequest.convertToEntity(request);
        assertThat(entity.getName()).isEqualTo(request.getName());
    }

    @Test
    public void convertToRequestDto() {
        Professional entity = Professional.builder().name("Test").build();
        ProfessionalRequest request = ProfessionalRequest.convertToRequestDto(entity);
        assertThat(request.getName()).isEqualTo(entity.getName());
    }
}
