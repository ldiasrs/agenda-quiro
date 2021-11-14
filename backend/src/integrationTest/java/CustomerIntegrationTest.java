import br.com.agendaquiro.controller.MessageHttpResponse;
import br.com.agendaquiro.domain.customer.Customer;
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


public class CustomerIntegrationTest {

    private String URL = "http://localhost:8080";

    private Customer customer = Customer.builder()
            .name("Test Customer")
            .phone("00922")
            .cpf("0092733")
            .height("176")
            .gender("male")
            .weight("1762")
            .birthDate(LocalDate.now())
            .email("customer@gmail.com")
            .build();

    private Customer anotherCustomer = Customer.builder()
            .name("Another Customer")
            .phone("001111")
            .cpf("0092222")
            .height("176")
            .gender("male")
            .weight("1762")
            .birthDate(LocalDate.now())
            .email("AnotherCustomer@gmail.com")
            .build();

    @Test
    public void crudTest() throws JSONException {
        long id = create(customer);
        listPagination("");
        customer = get(id);
        customer = edit(customer);
        listWihFilter();
        delete(customer);
    }

    @Test
    public void listWihFilter() throws JSONException {
        create(anotherCustomer);
        ResponseEntity<String> response = listPagination("Another");
        assertThat(response.getBody()).isNotEmpty();
        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONArray jsonCustomers = (JSONArray) jsonObject.get("content");
        for (int i = 0; i < jsonCustomers.length(); i++) {
            JSONObject jsonCustomer = (JSONObject) jsonCustomers.get(i);
            assertThat(jsonCustomer.get("name").toString().toLowerCase()).contains("another");
            Long id = Long.valueOf(jsonCustomer.get("id").toString());
            delete(Customer.builder().id(id).build());
        }
    }

    private ResponseEntity<String> listPagination(String searchTerm) {
        String endpoint = URL+"/customers";
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

    private void delete(Customer customerParam) {
        String endpoint = URL+"/customer/"+customerParam.getId();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MessageHttpResponse> response = restTemplate.exchange(endpoint, HttpMethod.DELETE, null, MessageHttpResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private Customer edit(Customer customer) {
        String endpoint = URL+"/customer/"+customer.getId();
        String newEmail = "newEmail@gmail.com";
        customer.setEmail(newEmail);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Customer> request = new HttpEntity<>(customer);
        ResponseEntity<Customer> response = restTemplate.exchange(endpoint, HttpMethod.PUT, request, Customer.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Customer editedCustomer = response.getBody();
        assertThat(editedCustomer.getEmail()).isEqualTo(newEmail);
        return editedCustomer;
    }

    private Customer get(long id) {
        String endpoint = URL+"/customer/"+id;
        RestTemplate restTemplate = new RestTemplate();
        Customer responseCustomer = restTemplate.getForObject(endpoint, Customer.class);
        assertThat(responseCustomer.getId()).isEqualTo(id);
        assertThat(responseCustomer.getGender()).isEqualTo(customer.getGender());
        assertThat(responseCustomer.getName()).isEqualTo(customer.getName());
        assertThat(responseCustomer.getEmail()).isEqualTo(customer.getEmail());
        return responseCustomer;
    }

    public Long create(Customer paramCustomer) {
        String endpoint = URL+"/customers";
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Customer> request = new HttpEntity<>(paramCustomer);
        ResponseEntity<MessageHttpResponse> response
                = restTemplate.postForEntity(endpoint, request, MessageHttpResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String id = response.getBody().getValue("id");
        assertThat(id).isNotBlank();
        return Long.valueOf(id);
    }
}
