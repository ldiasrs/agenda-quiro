package br.com.agendaquiro.domain.professsional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomRangeTimeBlocked {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
