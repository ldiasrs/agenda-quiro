package br.com.agendaquiro.controller.servicetype.request;

import br.com.agendaquiro.domain.servicetype.ServiceType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ServiceTypeRequestTest {

    @Test
    public void convertToEntity() {
        ServiceTypeRequest request = ServiceTypeRequest.builder()
                .description("Massagem")
                .durationInMinutes(60)
                .id(10L)
                .build();
        ServiceType entity = ServiceTypeRequest.convertToEntity(request);
        assertThat(entity.getDescription()).isEqualTo(request.getDescription());
        assertThat(entity.getDurationInMinutes()).isEqualTo(request.getDurationInMinutes());
        assertThat(entity.getId()).isEqualTo(request.getId());
    }

    @Test
    public void convertToRequestDto() {
        ServiceType entity = ServiceType.builder()
                .description("Massagem")
                .durationInMinutes(60)
                .id(10L)
                .build();
        ServiceTypeRequest request = ServiceTypeRequest.convertToRequestDto(entity);
        assertThat(entity.getDescription()).isEqualTo(request.getDescription());
        assertThat(entity.getDurationInMinutes()).isEqualTo(request.getDurationInMinutes());
        assertThat(entity.getId()).isEqualTo(request.getId());
    }
}
