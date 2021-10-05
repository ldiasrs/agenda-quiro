package br.com.agendaquiro.controller.customer.request;

import br.com.agendaquiro.domain.customer.Customer;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest implements Serializable {

    private Long id;

    private String name;
    private String email;
    private String phone;
    private String cpf;

    private LocalDate birthDate;
    private String height;
    private String weight;
    private String gender;


    public static Customer convertToEntity(CustomerRequest customerRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerRequest, Customer.class);
    }

    public static CustomerRequest convertToRequestDto(Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, CustomerRequest.class);
    }
}
