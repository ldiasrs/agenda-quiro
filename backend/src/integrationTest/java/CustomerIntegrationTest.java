import br.com.agendaquiro.domain.customer.Customer;
import org.json.JSONException;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


public class CustomerIntegrationTest {

    private static final IntegrationTestClient client =
            new IntegrationTestClient("customers", "customer");

    @Test
    public void crudTest() throws JSONException {
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
        //CREATE
        long id = client.create(customer);
        //LIST
        client.listPagination("");
        //GET
        Customer savedCustomer = client.get(id, Customer.class);
        //EDIT
        savedCustomer.setEmail("new@Email.com");
        Customer editedCustomer = client.edit(savedCustomer, savedCustomer.getId());
        assertThat(editedCustomer.getEmail()).isEqualTo(savedCustomer.getEmail());
        //DELETE
        client.delete(editedCustomer.getId());
    }

    @Test
    public void listWihFilter() throws JSONException {
        //CREATE
        Customer anotherCustomer = Customer.builder()
                .name("Another Customer")
                .phone("001111")
                .cpf("0092222")
                .height("176")
                .gender("male")
                .weight("1762")
                .birthDate(LocalDate.now())
                .email("AnotherCustomer@gmail.com")
                .build();
        client.create(anotherCustomer);
        //LIST WITH FILTER
        client.listWihFilterAndRemoveFoundEntities("name");
    }
}
