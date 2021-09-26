package br.com.agendaquiro.domain.to;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
public class CustomerTO implements Serializable,TransferableDataObject {

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
