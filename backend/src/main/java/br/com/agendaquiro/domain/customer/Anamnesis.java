package br.com.agendaquiro.domain.customer;

import br.com.agendaquiro.domain.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Anamnesis {
    private String description;
    private Customer customer;
    private List<String> tags;
}
