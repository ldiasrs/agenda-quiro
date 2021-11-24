import br.com.agendaquiro.domain.servicetype.ServiceType;
import org.json.JSONException;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


public class CalendarIntegrationTest {

    private static final IntegrationTestClient client =
            new IntegrationTestClient("calendar", "calendar");

    @Test
    public void list() throws JSONException {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<T> request = new HttpEntity<>(editEntity);
//        ResponseEntity<T> response = restTemplate.exchange(endpoint, HttpMethod.PUT, request, (Class<T>) editEntity.getClass());
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isNotNull();
    }


}
