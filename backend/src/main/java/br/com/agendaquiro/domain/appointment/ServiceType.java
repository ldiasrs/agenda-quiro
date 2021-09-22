package br.com.agendaquiro.domain.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceType {
    private String description;
    private int durationInMinutes;
}
