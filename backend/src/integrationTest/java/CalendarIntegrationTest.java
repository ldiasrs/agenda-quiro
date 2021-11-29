import br.com.agendaquiro.controller.calendar.CalendarResponse;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


public class CalendarIntegrationTest {

    @Test
    public void shouldGetCalendarByUser()  {
        RestTemplate restTemplate = new RestTemplate();
        CalendarResponse response = restTemplate.getForObject(IntegrationTestClient.URL+"/calendar/user/1", CalendarResponse.class);
        assertThat(response).isNotNull();
    }
}
