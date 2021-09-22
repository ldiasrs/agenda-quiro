package br.com.agendaquiro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Customer {
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private LocalDate birthDate;
    private String height;
    private String weight;
    private String gender;
}
