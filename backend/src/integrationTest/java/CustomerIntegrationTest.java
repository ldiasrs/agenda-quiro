import br.com.agendaquiro.controller.MessageHttpResponse;
import br.com.agendaquiro.domain.customer.Customer;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

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

    @Test
    public void crudTest() {
        long id = create();
        listAll();
        customer = get(id);
        customer = edit(customer);
        delete(customer);
    }

    private void listAll() {
        String endpoint = URL+"/customers";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.GET, null, String.class);
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private void delete(Customer customer) {
        String endpoint = URL+"/customer/"+customer.getId();
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

    public Long create() {
        String endpoint = URL+"/customer";
        RestTemplate restTemplate = new RestTemplate();


        HttpEntity<Customer> request = new HttpEntity<>(customer);
        ResponseEntity<MessageHttpResponse> response
                = restTemplate.postForEntity(endpoint, request, MessageHttpResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String id = response.getBody().getValue("id");
        assertThat(id).isNotBlank();
        return Long.valueOf(id);
    }
}
