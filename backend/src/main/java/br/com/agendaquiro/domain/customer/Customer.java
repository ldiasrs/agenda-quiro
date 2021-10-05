package br.com.agendaquiro.domain.customer;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String cpf;

    private LocalDate birthDate;
    private String height;
    private String weight;
    private String gender;
}
