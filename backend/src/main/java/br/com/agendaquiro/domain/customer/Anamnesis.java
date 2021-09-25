package br.com.agendaquiro.domain.customer;

import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.repository.converter.ListDayOfWeekToIntegerConverter;
import br.com.agendaquiro.repository.converter.ListTagStringToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "anamnesis")
public class Anamnesis {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String description;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Convert(converter = ListTagStringToStringConverter.class)
    private List<String> tags;
}
