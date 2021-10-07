package br.com.agendaquiro.domain.customer;

import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.repository.converter.ListDayOfWeekToIntegerConverter;
import br.com.agendaquiro.repository.converter.ListTagStringToStringConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
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
