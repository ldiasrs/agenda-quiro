import br.com.agendaquiro.controller.MessageHttpResponse;
import br.com.agendaquiro.controller.professional.request.ProfessionalRequest;
import br.com.agendaquiro.domain.customer.Customer;
import br.com.agendaquiro.domain.professsional.Professional;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


public class ProfessionalIntegrationTest {

    private String URL = "http://localhost:8080";

    private ProfessionalRequest professionalRequest = ProfessionalRequest.builder()
            .name("Test Professional")
            .build();

    @Test
    public void crudTest() {
        long id = create(professionalRequest);
        listPagination("");
        ProfessionalRequest professionalResponse = get(id);
        delete(professionalResponse);
    }

    private ResponseEntity<String> listPagination(String searchTerm) {
        String endpoint = URL+"/professional";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("searchTerm", searchTerm)
                .queryParam("page", 0)
                .queryParam("size", 10);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, null, String.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        return response;
    }

    private void delete(ProfessionalRequest professionalRequest) {
        String endpoint = URL+"/professional/"+professionalRequest.getId();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MessageHttpResponse> response = restTemplate.exchange(endpoint, HttpMethod.DELETE, null, MessageHttpResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private ProfessionalRequest get(long id) {
        String endpoint = URL+"/professional/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ProfessionalRequest professionalRequest = restTemplate.getForObject(endpoint, ProfessionalRequest.class);
        assertThat(professionalRequest.getId()).isEqualTo(id);
        assertThat(professionalRequest.getName()).isEqualTo(professionalRequest.getName());
        return professionalRequest;
    }

    public Long create(ProfessionalRequest paramCustomer) {
        String endpoint = URL+"/professional";
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<ProfessionalRequest> request = new HttpEntity<>(paramCustomer);
        ResponseEntity<MessageHttpResponse> response
                = restTemplate.postForEntity(endpoint, request, MessageHttpResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String id = response.getBody().getValue("id");
        assertThat(id).isNotBlank();
        return Long.valueOf(id);
    }
}
