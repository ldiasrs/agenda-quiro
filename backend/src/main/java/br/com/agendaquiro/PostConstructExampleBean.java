package br.com.agendaquiro;

import br.com.agendaquiro.domain.daysofweekblocked.DayOfWeekTimeBlocked;
import br.com.agendaquiro.repository.DayOfWeekTimeBlockedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class PostConstructExampleBean {
    @Autowired
    private DayOfWeekTimeBlockedRepository repository;

    private final Logger LOG =
            Logger.getLogger(PostConstructExampleBean.class.getName());
    @PostConstruct
    public void init() {
        DayOfWeekTimeBlocked saved = repository.save(DayOfWeekTimeBlocked.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(1))
                .build());
        Optional<DayOfWeekTimeBlocked> fromdb = repository.findById(saved.getId());
        LOG.info(fromdb.get().toString());
    }
}
