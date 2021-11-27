package br.com.agendaquiro.controller.customer.request;

import br.com.agendaquiro.domain.customer.Customer;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerRequestTest {

    @Test
    public void convertToEntity() {
        CustomerRequest customerRequest = CustomerRequest.builder()
                .cpf("0000")
                .birthDate(LocalDate.now())
                .email("leo@gmail.com")
                .height("192")
                .weight("122")
                .name("Leo")
                .phone("99828282")
                .id(1L)
                .gender("male")
                .build();
        Customer customer = CustomerRequest.convertToEntity(customerRequest);
        assertThat(customer.getCpf()).isEqualTo(customerRequest.getCpf());
        assertThat(customer.getBirthDate()).isEqualTo(customerRequest.getBirthDate());
        assertThat(customer.getEmail()).isEqualTo(customerRequest.getEmail());
        assertThat(customer.getHeight()).isEqualTo(customerRequest.getHeight());
        assertThat(customer.getWeight()).isEqualTo(customerRequest.getWeight());
        assertThat(customer.getName()).isEqualTo(customerRequest.getName());
        assertThat(customer.getPhone()).isEqualTo(customerRequest.getPhone());
        assertThat(customer.getGender()).isEqualTo(customerRequest.getGender());
        assertThat(customer.getId()).isEqualTo(customerRequest.getId());
    }

    @Test
    public void convertToRequestDto() {
        Customer customer = Customer.builder()
                .cpf("0000")
                .birthDate(LocalDate.now())
                .email("leo@gmail.com")
                .height("192")
                .weight("122")
                .name("Leo")
                .phone("99828282")
                .id(1L)
                .gender("male")
                .build();
        CustomerRequest customerRequest = CustomerRequest.convertToRequestDto(customer);
        assertThat(customerRequest.getCpf()).isEqualTo(customer.getCpf());
        assertThat(customerRequest.getBirthDate()).isEqualTo(customer.getBirthDate());
        assertThat(customerRequest.getEmail()).isEqualTo(customer.getEmail());
        assertThat(customerRequest.getHeight()).isEqualTo(customer.getHeight());
        assertThat(customerRequest.getWeight()).isEqualTo(customer.getWeight());
        assertThat(customerRequest.getName()).isEqualTo(customer.getName());
        assertThat(customerRequest.getPhone()).isEqualTo(customer.getPhone());
        assertThat(customerRequest.getGender()).isEqualTo(customer.getGender());
        assertThat(customerRequest.getId()).isEqualTo(customer.getId());
    }
}
