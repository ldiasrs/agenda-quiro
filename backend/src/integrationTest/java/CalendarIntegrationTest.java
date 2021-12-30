import br.com.agendaquiro.controller.calendar.CalendarResponse;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;


public class CalendarIntegrationTest {

    @Test
    public void shouldGetCalendarByUser()  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String endpoint = IntegrationTestClient.URL + "/calendar/user/1";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("searchTerm", "aaa")
                .queryParam("startPeriod", LocalDate.now()
                        .minusDays(1).format(formatter))
                .queryParam("endPeriod", LocalDateTime.now()
                        .plusDays(1).format(formatter));
        RestTemplate restTemplate = new RestTemplate();
        CalendarResponse response = restTemplate.getForObject(builder.toUriString(), CalendarResponse.class);
        assertThat(response).isNotNull();
    }
}
