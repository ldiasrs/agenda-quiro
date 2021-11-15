package br.com.agendaquiro.controller.servicetype.request;

import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.servicetype.ServiceType;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceTypeRequest implements Serializable {

    private Long id;
    private String description;
    private int durationInMinutes;


    public static ServiceType convertToEntity(ServiceTypeRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(request, ServiceType.class);
    }

    public static ServiceTypeRequest convertToRequestDto(ServiceType customer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, ServiceTypeRequest.class);
    }
}
