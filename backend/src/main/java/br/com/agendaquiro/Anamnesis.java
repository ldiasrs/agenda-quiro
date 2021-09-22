package br.com.agendaquiro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Anamnesis {
    private String description;
    private Customer customer;
    private List<String> tags;
}
