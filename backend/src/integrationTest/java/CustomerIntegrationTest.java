import br.com.agendaquiro.controller.MessageHttpResponse;
import br.com.agendaquiro.domain.customer.Customer;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CustomerIntegrationTest {

    private String URL = "http://localhost:8080";

    @Test
    public void shouldCreateListAndDeleteCustomer() {
        RestTemplate restTemplate = new RestTemplate();
        String endpoint = URL+"/customer";
        Customer customer = Customer.builder()
                .name("Test Customer")
                .phone("00922")
                .cpf("0092733")
                .height("176")
                .gender("male")
                .weight("1762")
                .birthDate(LocalDate.now())
                .email("customer@gmail.com")
                .build();
        HttpEntity<Customer> request = new HttpEntity<>(customer);
        ResponseEntity<MessageHttpResponse> response
                = restTemplate.postForEntity(endpoint, request, MessageHttpResponse.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }
}
