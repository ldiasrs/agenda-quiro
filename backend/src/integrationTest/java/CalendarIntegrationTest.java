import br.com.agendaquiro.controller.calendar.CalendarResponse;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


public class CalendarIntegrationTest {

    @Test
    public void list()  {
        RestTemplate restTemplate = new RestTemplate();
        CalendarResponse response = restTemplate.getForObject(IntegrationTestClient.URL+"/calendar/1", CalendarResponse.class);
        assertThat(response).isNotNull();
    }
}
